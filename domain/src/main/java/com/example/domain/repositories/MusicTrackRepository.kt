package com.example.domain.repositories

import com.example.domain.models.MusicTrackData
import kotlinx.coroutines.flow.Flow

interface MusicTrackRepository {
    suspend fun addMusicTrack(newTrack: MusicTrackData)

    suspend fun deleteMusicTrack(trackToDelete: MusicTrackData)

    fun getAllTracksOrderedByNames() : Flow<List<MusicTrackData>>
}