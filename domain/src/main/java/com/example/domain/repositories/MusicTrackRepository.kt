package com.example.domain.repositories

import com.example.domain.models.MusicTrackData

interface MusicTrackRepository {
    suspend fun addMusicTrack(newTrack: MusicTrackData)
    suspend fun deleteMusicTrack(trackToDelete: MusicTrackData)
}