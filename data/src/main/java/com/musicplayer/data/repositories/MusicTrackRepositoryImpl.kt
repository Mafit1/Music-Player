package com.musicplayer.data.repositories

import android.content.Context
import android.media.MediaMetadataRetriever
import android.util.Log
import com.musicplayer.data.dao.MusicTrackDAO
import com.musicplayer.data.models.MusicTrackEntity
import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.repositories.MusicTrackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.File

class MusicTrackRepositoryImpl(
    private val context: Context,
    private val musicTrackDAO: MusicTrackDAO
) : MusicTrackRepository {
    // мб не нужен
    override suspend fun addMusicTrack(newTrack: MusicTrackData) = musicTrackDAO.upsertTrack(
        MusicTrackEntity(
            id = newTrack.id,
            name = newTrack.name,
            author = newTrack.author,
            duration = newTrack.duration,
            playlistId = newTrack.playlistId,
            filePath = newTrack.filePath
        )
    )

    override suspend fun addMusicTracks(musicTracksList: List<MusicTrackData>) {

    }

    override suspend fun deleteMusicTrack(trackToDelete: MusicTrackData) = musicTrackDAO.deleteTrack(
        MusicTrackEntity(
            id = trackToDelete.id,
            name = trackToDelete.name,
            author = trackToDelete.author,
            duration = trackToDelete.duration,
            playlistId = trackToDelete.playlistId,
            filePath = trackToDelete.filePath
        )
    )

    override fun getAllTracksOrderedByNames(): Flow<List<MusicTrackData>> = musicTrackDAO.getAllTracksOrderedByNames().map { list ->
        list.map { it.toDomain() }
    }

    override suspend fun setPlaylistToTrack(playlist: PlaylistInfo, musicTrackId: Int) = musicTrackDAO.updateTrackSetPlaylistId(playlist.id, musicTrackId)

    override suspend fun saveTrackFile(file: File) {
        val metaData = getTrackMetaData(file)

        val musicDirectory = createMusicDirectory()

        val destination = File(musicDirectory, file.name)

        withContext(Dispatchers.IO) {
            file.copyTo(destination, overwrite = true)
        }

        musicTrackDAO.upsertTrack(
            MusicTrackEntity(
                name = metaData.name,
                author = metaData.author,
                duration = metaData.duration,
                filePath = destination.absolutePath
            )
        )
        Log.d("filepath", destination.absolutePath)
    }

    private fun createMusicDirectory(): File {
        val musicDir = File(context.filesDir, "music")
        if (!musicDir.exists()) musicDir.mkdirs()
        return musicDir
    }

    private fun getTrackMetaData(file: File): MusicTrackData {
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(file.absolutePath)

        val title = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE) ?: "Unknown Title"
        val artist = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST) ?: "Unknown Artist"
        val duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toLong() ?: 0L

        return MusicTrackData(
            name = title,
            author = artist,
            duration = duration
        )
    }
}