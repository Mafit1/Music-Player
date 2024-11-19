package com.musicplayer.app.viewmodels

import android.content.Context
import android.content.Intent
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.usecases.AddTrackToPlaylist
import com.musicplayer.domain.usecases.DeleteTrackFromTrackList
import com.musicplayer.domain.usecases.GetAllTracksOrderedById
import com.musicplayer.domain.usecases.AddTrackToTrackList
import com.musicplayer.domain.usecases.GetAllPlaylistsOrderedByNames
import com.musicplayer.domain.usecases.UpdateTrackFields
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FullTrackListViewModel(
    private val getAllTracksOrderedByNames: GetAllTracksOrderedById,
    private val addTrackToTrackList: AddTrackToTrackList,
    private val deleteTrackFromTrackList: DeleteTrackFromTrackList,
    private val addTrackToPlaylist: AddTrackToPlaylist,
    private val getAllPlaylistsOrderedByNames: GetAllPlaylistsOrderedByNames,
    private val updateTrackFields: UpdateTrackFields
) : ViewModel() {

    fun getAllTracksOrderedByNames(): Flow<List<MusicTrackData>> {
        return getAllTracksOrderedByNames.execute()
    }

    fun getAllPlaylists(): Flow<List<PlaylistInfo>> {
        return getAllPlaylistsOrderedByNames.execute()
    }

    fun addTrackToTrackList(newTrack: MusicTrackData) = viewModelScope.launch {
        addTrackToTrackList.execute(newTrack)
    }

    fun addTrackToPlaylist(track: MusicTrackData, playlist: PlaylistInfo) = viewModelScope.launch {
        addTrackToPlaylist.execute(track, playlist)
    }

    fun updateTrackFields(track: MusicTrackData, newName: String, newAuthor: String) = viewModelScope.launch {
        updateTrackFields.execute(track, newName, newAuthor)
    }

    fun deleteTrack(trackToDelete: MusicTrackData) = viewModelScope.launch {
        deleteTrackFromTrackList.execute(trackToDelete)
    }

    fun handleSelectedUris(context: Context, uris: List<Uri>, tracks: MutableList<MusicTrackData>) =
        viewModelScope.launch {
            val contentResolver = context.contentResolver

            uris.forEach { uri ->
                try {
                    contentResolver.takePersistableUriPermission(
                        uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION
                                or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                } catch (e: SecurityException) {
                    e.printStackTrace()
                }
            }

            val newTracks = uris.mapNotNull { uri ->
                getTrackMetadata(context, uri)
            }
            tracks.addAll(newTracks)
            newTracks.forEach { track ->
                addTrackToTrackList.execute(track)
                Log.d("myMusicPlayer", track.toString())
            }
        }
}

private fun getTrackMetadata(context: Context, uri: Uri): MusicTrackData? {
    val retriever = MediaMetadataRetriever()
    try {
        retriever.setDataSource(context, uri)
        val title =
            retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE) ?: "Без названия"
        val artist =
            retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST) ?: "Неизвестен"
        val duration =
            retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toLongOrNull()
                ?: 0L
        val uriString = uri.toString()

        return MusicTrackData(
            name = title,
            author = artist,
            duration = duration,
            filePath = uriString
        )
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    } finally {
        retriever.release()
    }
}
