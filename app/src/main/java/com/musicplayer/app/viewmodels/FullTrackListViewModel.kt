package com.musicplayer.app.viewmodels

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.usecases.DeleteTrackFromTrackList
import com.musicplayer.domain.usecases.GetAllTracksOrderedByNames
import com.musicplayer.domain.usecases.AddTrackToTrackList
import com.musicplayer.domain.usecases.SaveTrackFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class FullTrackListViewModel(
    private val getAllTracksOrderedByNames: GetAllTracksOrderedByNames,
    private val addTrackToTrackList: AddTrackToTrackList,
    private val saveTrackFile: SaveTrackFile,
    private val deleteTrackFromTrackList: DeleteTrackFromTrackList
) : ViewModel() {

    private var contentResolver: ContentResolver? = null

    fun setContentResolver(contentResolver: ContentResolver) {
        this.contentResolver = contentResolver
    }

    fun getAllTracksOrderedByNames() : Flow<List<MusicTrackData>> {
        return getAllTracksOrderedByNames.execute()
    }

    fun addTrackToTrackList(newTrack: MusicTrackData) = viewModelScope.launch {
        addTrackToTrackList.execute(newTrack)
    }

    fun saveTrackToTrackList(file: File) = viewModelScope.launch {
        saveTrackFile.execute(file)
    }

    fun loadTracks() = viewModelScope.launch {
        val projection = arrayOf(
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATA
        )

        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"
        val sortOrder = "${MediaStore.Audio.Media.DATE_ADDED} DESC"

        contentResolver?.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            sortOrder
        )?.use { cursor ->
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)
            val authorColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
            val pathColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)

            while (cursor.moveToNext()) {
                val name = cursor.getString(nameColumn)
                val author = cursor.getString(authorColumn)
                val duration = cursor.getLong(durationColumn)
                val path = cursor.getString(pathColumn)

                addTrackToTrackList.execute(
                    MusicTrackData(
                        name = name,
                        author = author,
                        duration = duration,
                        filePath = path
                    )
                )
            }
        }
    }

    fun deleteTrack(trackToDelete: MusicTrackData) = viewModelScope.launch {
        deleteTrackFromTrackList.execute(trackToDelete)
    }

    fun handleSelectedUris(context: Context, uris: List<Uri>) = viewModelScope.launch {
        val contentResolver = context.contentResolver

        uris.forEach { uri ->
            try {
                contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                )
            } catch (e: SecurityException) {
                e.printStackTrace()
            }
        }

        val newTracks = uris.mapNotNull { uri ->
            uri.getTrackMetadata(context)
        }
        newTracks.forEach { track ->
            addTrackToTrackList.execute(track)
            Log.d("myMusicPlayer", track.toString())
        }
    }
}

private suspend fun Uri.getTrackMetadata(context: Context): MusicTrackData? {
    return withContext(Dispatchers.IO) {
        val retriever = MediaMetadataRetriever()
        try {
            retriever.setDataSource(context, this@getTrackMetadata)
            val title = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE) ?: "Без названия"
            val artist = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST) ?: "Неизвестен"
            val duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toLongOrNull() ?: 0L
            val uriString = this@getTrackMetadata.toString()

            MusicTrackData(
                name = title,
                author = artist,
                duration = duration,
                filePath = uriString
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            retriever.release()
        }
    }
}
