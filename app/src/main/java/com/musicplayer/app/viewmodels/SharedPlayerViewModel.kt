package com.musicplayer.app.viewmodels

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.repositories.PlayerManagerRepository
import kotlinx.coroutines.flow.Flow

class SharedPlayerViewModel(
    private val playerManagerRepository: PlayerManagerRepository
) : ViewModel() {

    val currentPosition: Flow<Long> = playerManagerRepository.getCurrentPosition()
    val duration: Flow<Long> = playerManagerRepository.getDuration()
    val isPlaying: Flow<Boolean> = playerManagerRepository.getIsPlaying()
    val currentTrackIndex: Flow<Int> = playerManagerRepository.getCurrentTrackIndex()
    val isShuffleEnabled: Flow<Boolean> = playerManagerRepository.getIsShuffleEnabled()
    val repeatMode: Flow<Int> = playerManagerRepository.getRepeatMode()
    val currentPlaylist: Flow<List<MusicTrackData>> = playerManagerRepository.getCurrentPlaylist()
    val currentPlayerState: Flow<Int> = playerManagerRepository.getCurrentPlayerState()

    fun playTrackAt(index: Int) {
        playerManagerRepository.playTrackAt(index)
    }

    fun pause() {
        playerManagerRepository.pause()
    }

    fun play() {
        playerManagerRepository.play()
    }

    fun nextTrack() {
        playerManagerRepository.nextTrack()
    }

    fun previousTrack() {
        playerManagerRepository.previousTrack()
    }

    fun seekTo(positionMs: Long) {
        playerManagerRepository.seekTo(positionMs)
    }

    fun toggleShuffle() {
        playerManagerRepository.toggleShuffle()
    }

    fun toggleRepeatMode() {
        playerManagerRepository.toggleRepeatMode()
    }

    fun startUpdatingProgress() {
        playerManagerRepository.startUpdatingProgress()
    }

    fun stopUpdatingProgress() {
        playerManagerRepository.stopUpdatingProgress()
    }

    fun setPlaylist(tracks: List<MusicTrackData>) {
        playerManagerRepository.setPlaylist(tracks)
    }

    override fun onCleared() {
        super.onCleared()
        playerManagerRepository.release()
    }
}