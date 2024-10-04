package com.example.data.repositories

import com.example.domain.models.MusicTrackData
import com.example.domain.repositories.MusicTrackRepository

class MusicTrackRepositoryImpl : MusicTrackRepository {
    override suspend fun addMusicTrack(newTrack: MusicTrackData) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMusicTrack(trackToDelete: MusicTrackData) {
        TODO("Not yet implemented")
    }
}