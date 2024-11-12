package com.musicplayer.domain.repositories

import com.musicplayer.domain.models.MusicTrackData
import kotlinx.coroutines.flow.Flow

interface PlayerManagerRepository {

    fun playTrackAt(index: Int)

    fun pause()

    fun play()

    fun nextTrack()

    fun previousTrack()

    fun toggleShuffle()

    fun toggleRepeatMode()

    fun seekTo(positionMs: Long)

    fun startUpdatingProgress()

    fun stopUpdatingProgress()

    fun getCurrentPosition(): Flow<Long>

    fun getDuration(): Flow<Long>

    fun getIsPlaying(): Flow<Boolean>

    fun getCurrentTrackIndex(): Flow<Int>

    fun getCurrentPlaylist(): Flow<List<MusicTrackData>>

    fun getIsShuffleEnabled(): Flow<Boolean>

    fun getRepeatMode(): Flow<Int>

    fun getCurrentPlayerState(): Flow<Int>

    fun setPlaylist(tracks: List<MusicTrackData>)

    fun addTrack(track: MusicTrackData)

    fun release()
}