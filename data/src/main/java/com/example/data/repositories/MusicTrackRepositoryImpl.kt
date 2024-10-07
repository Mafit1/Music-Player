package com.example.data.repositories

import com.example.data.dao.MusicTrackDAO
import com.example.domain.models.MusicTrackData
import com.example.domain.repositories.MusicTrackRepository
import kotlinx.coroutines.flow.Flow

class MusicTrackRepositoryImpl(
    musicTrackDAO: MusicTrackDAO
) : MusicTrackRepository {
    override suspend fun addMusicTrack(newTrack: MusicTrackData) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMusicTrack(trackToDelete: MusicTrackData) {
        TODO("Not yet implemented")
    }

    override fun getAllTracksOrderedByNames(): Flow<List<MusicTrackData>> {
        TODO("Not yet implemented")
    }
}