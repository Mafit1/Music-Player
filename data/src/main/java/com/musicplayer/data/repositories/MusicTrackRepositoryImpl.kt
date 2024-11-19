package com.musicplayer.data.repositories

import com.musicplayer.data.dao.MusicTrackDAO
import com.musicplayer.data.models.MusicTrackEntity
import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.repositories.MusicTrackRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MusicTrackRepositoryImpl(
    private val musicTrackDAO: MusicTrackDAO
) : MusicTrackRepository {

    override suspend fun addMusicTrack(newTrack: MusicTrackData) = musicTrackDAO.upsertTrack(
        MusicTrackEntity(
            id = newTrack.id,
            name = newTrack.name,
            author = newTrack.author,
            duration = newTrack.duration,
            filePath = newTrack.filePath
        )
    )

    override suspend fun addMusicTracks(musicTracksList: List<MusicTrackData>) {

    }

    override suspend fun deleteMusicTrack(trackToDelete: MusicTrackData) {
        musicTrackDAO.deleteTrack(
            MusicTrackEntity(
                id = trackToDelete.id,
                name = trackToDelete.name,
                author = trackToDelete.author,
                duration = trackToDelete.duration,
                filePath = trackToDelete.filePath
            )
        )

    }

    override suspend fun updateTrackFields(trackId: Int, name: String, author: String) {
        musicTrackDAO.updateTrackFields(trackId, name, author)
    }

    override fun getAllTracksOrderedById(): Flow<List<MusicTrackData>> = musicTrackDAO.getAllTracksOrderedById().map { list ->
        list.map { it.toDomain() }
    }

    override suspend fun getTrackById(trackId: Int): MusicTrackData {
        return musicTrackDAO.getTrackById(trackId).toDomain()
    }
}