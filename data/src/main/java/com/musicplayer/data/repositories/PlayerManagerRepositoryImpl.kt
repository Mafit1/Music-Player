package com.musicplayer.data.repositories

import android.content.Context
import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.common.Timeline
import androidx.media3.common.Tracks
import androidx.media3.exoplayer.ExoPlayer
import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.repositories.PlayerManagerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PlayerManagerRepositoryImpl(
    private val exoPlayer: ExoPlayer
) : PlayerManagerRepository {

    private val _currentPosition = MutableStateFlow(0L)
    private val _duration = MutableStateFlow(0L)
    private val _isPlaying = MutableStateFlow(false)
    private val _currentTrackIndex = MutableStateFlow(0)
    private val _shuffleEnabled = MutableStateFlow(false)
    private val _repeatMode = MutableStateFlow(Player.REPEAT_MODE_OFF)
    private val _tracks = MutableStateFlow<List<MusicTrackData>>(emptyList())
    private val _currentPlayerState = MutableStateFlow(Player.STATE_IDLE)


    override fun getCurrentPosition(): Flow<Long> = _currentPosition.asStateFlow()
    override fun getDuration(): Flow<Long> = _duration.asStateFlow()
    override fun getIsPlaying(): Flow<Boolean> = _isPlaying.asStateFlow()
    override fun getCurrentTrackIndex(): Flow<Int> = _currentTrackIndex.asStateFlow()
    override fun getIsShuffleEnabled(): Flow<Boolean> = _shuffleEnabled.asStateFlow()
    override fun getRepeatMode(): Flow<Int> = _repeatMode.asStateFlow()
    override fun getCurrentPlaylist(): Flow<List<MusicTrackData>> = _tracks.asStateFlow()
    override fun getCurrentPlayerState(): Flow<Int> = _currentPlayerState.asStateFlow()


    private var progressJob: Job? = null

    init {
        exoPlayer.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                _isPlaying.value = isPlaying
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                _currentPlayerState.value = playbackState
                if (exoPlayer.playWhenReady) {
                    _duration.value = exoPlayer.duration
                    updateTrackDuration()
                }
                Log.d("myMusicPlayer", "state: $playbackState")
            }

            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                _currentTrackIndex.value = exoPlayer.currentMediaItemIndex
                updateTrackDuration()
            }

            override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {
                _shuffleEnabled.value = shuffleModeEnabled
                Log.d("myMusicPlayer", "shuffle: $shuffleModeEnabled")
            }

            override fun onRepeatModeChanged(repeatMode: Int) {
                _repeatMode.value = repeatMode
                Log.d("myMusicPlayer", "repeat mode: $repeatMode")
            }
        })
    }

    override fun playTrackAt(index: Int) {
        exoPlayer.seekTo(index, 0)
        exoPlayer.play()
    }

    override fun pause() {
        exoPlayer.pause()
    }

    override fun play() {
        exoPlayer.play()
    }

    override fun nextTrack() {
        exoPlayer.seekToNext()
    }

    override fun previousTrack() {
        exoPlayer.seekToPrevious()
    }

    override fun toggleShuffle() {
        exoPlayer.shuffleModeEnabled = !exoPlayer.shuffleModeEnabled
    }

    override fun toggleRepeatMode() {
        val newMode = when (exoPlayer.repeatMode) {
            Player.REPEAT_MODE_OFF -> Player.REPEAT_MODE_ALL
            Player.REPEAT_MODE_ALL -> Player.REPEAT_MODE_ONE
            else -> Player.REPEAT_MODE_OFF
        }
        exoPlayer.repeatMode = newMode
    }

    override fun seekTo(positionMs: Long) {
        exoPlayer.seekTo(positionMs)
        _currentPosition.value = positionMs
    }

    override fun startUpdatingProgress() {
        if (progressJob == null || progressJob?.isActive == false) {
            progressJob = CoroutineScope(Dispatchers.Main).launch {
                while (true) {
                    if (exoPlayer.isPlaying) {
                        _currentPosition.value = exoPlayer.currentPosition
                    }
                    delay(1000L)
                }
            }
        }
    }

    override fun stopUpdatingProgress() {
        progressJob?.cancel()
        progressJob = null
    }

    private fun updateTrackDuration() {
        val duration = exoPlayer.duration.coerceAtLeast(0L)
        _duration.value = duration
    }

    override fun setPlaylist(tracks: List<MusicTrackData>) {
        _tracks.value = tracks
        val mediaItems = tracks.map { track ->
            MediaItem.fromUri(track.filePath)
        }
        exoPlayer.setMediaItems(mediaItems)
        exoPlayer.prepare()
        Log.d("myMusicPlayer", "player prepared")
    }

    override fun addTrack(track: MusicTrackData) {
        exoPlayer.addMediaItem(MediaItem.fromUri(track.filePath))
    }

    override fun addTracks(tracks: List<MusicTrackData>) {
        val mediaItems = tracks.map { track ->
            MediaItem.fromUri(track.filePath)
        }
        exoPlayer.addMediaItems(mediaItems)
    }

    override fun release() {
        exoPlayer.release()
        stopUpdatingProgress()
    }
}