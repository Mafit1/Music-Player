package com.example.data.repositories

import com.example.data.dao.MusicTrackDAO
import com.example.data.models.MusicTrackEntity
import com.example.domain.models.MusicTrackData
import com.example.domain.repositories.MusicTrackRepository
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
            durationSec = newTrack.durationSec,
            playlistId = newTrack.playlistId
        )
    )

    override suspend fun deleteMusicTrack(trackToDelete: MusicTrackData) {
        TODO("Not yet implemented")
    }

    override fun getAllTracksOrderedByNames(): Flow<List<MusicTrackData>> = musicTrackDAO.getAllTracksOrderedByNames().map { list ->
        list.map { it.toDomain()}
    }
}