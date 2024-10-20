package com.musicplayer.domain.repositories

import com.musicplayer.domain.models.MusicTrackData
import kotlinx.coroutines.flow.Flow

interface MusicTrackRepository {
    suspend fun addMusicTrack(newTrack: MusicTrackData)

    suspend fun deleteMusicTrack(trackToDelete: MusicTrackData)

    fun getAllTracksOrderedByNames() : Flow<List<MusicTrackData>>
}