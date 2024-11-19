package com.musicplayer.domain.repositories

import com.musicplayer.domain.models.MusicTrackData
import kotlinx.coroutines.flow.Flow

interface MusicTrackRepository {

    suspend fun addMusicTrack(newTrack: MusicTrackData)

    suspend fun addMusicTracks(musicTracksList: List<MusicTrackData>)

    suspend fun deleteMusicTrack(trackToDelete: MusicTrackData)

    suspend fun updateTrackFields(trackId: Int, name: String, author: String)

    fun getAllTracksOrderedById(): Flow<List<MusicTrackData>>

    suspend fun getTrackById(trackId: Int): MusicTrackData
}