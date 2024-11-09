package com.musicplayer.domain.repositories

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

}