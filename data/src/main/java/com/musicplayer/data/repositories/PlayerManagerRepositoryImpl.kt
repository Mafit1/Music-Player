package com.musicplayer.data.repositories

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.musicplayer.domain.repositories.PlayerManagerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlayerManagerRepositoryImpl(
    context: Context
) : PlayerManagerRepository {

    private val exoPlayer: ExoPlayer = ExoPlayer.Builder(context).build()

    // StateFlows для наблюдения за состоянием
    private val _currentPosition = MutableStateFlow(0L)
    private val _duration = MutableStateFlow(0L)
    private val _isPlaying = MutableStateFlow(false)
    private val _currentTrackIndex = MutableStateFlow(0)
    private val _shuffleEnabled = MutableStateFlow(false)
    private val _repeatMode = MutableStateFlow(Player.REPEAT_MODE_OFF)

    // Публичные Flow для доступа
    override fun getCurrentPosition(): Flow<Long> = _currentPosition.asStateFlow()
    override fun getDuration(): Flow<Long> = _duration.asStateFlow()
    override fun getIsPlaying(): Flow<Boolean> = _isPlaying.asStateFlow()
    override fun getCurrentTrackIndex(): Flow<Int> = _currentTrackIndex.asStateFlow()
    val isShuffleEnabled: Flow<Boolean> = _shuffleEnabled.asStateFlow()
    val repeatMode: Flow<Int> = _repeatMode.asStateFlow()

    // Корутинная задача для обновления текущей позиции
    private var progressJob: Job? = null

    init {
        // Настраиваем слушателя ExoPlayer
        exoPlayer.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                _isPlaying.value = isPlaying
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_READY) {
                    _duration.value = exoPlayer.duration
                }
            }

            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                _currentTrackIndex.value = exoPlayer.currentMediaItemIndex
            }

            override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {
                _shuffleEnabled.value = shuffleModeEnabled
            }

            override fun onRepeatModeChanged(repeatMode: Int) {
                _repeatMode.value = repeatMode
            }
        })
    }

    // Методы управления треками
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
            Player.REPEAT_MODE_OFF -> Player.REPEAT_MODE_ONE
            Player.REPEAT_MODE_ONE -> Player.REPEAT_MODE_ALL
            else -> Player.REPEAT_MODE_OFF
        }
        exoPlayer.repeatMode = newMode
    }

    override fun seekTo(positionMs: Long) {
        exoPlayer.seekTo(positionMs)
        _currentPosition.value = positionMs
    }

    // Обновление позиции трека
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

    // Метод для загрузки списка треков в ExoPlayer
    fun setPlaylist(tracks: List<String>) {
        val mediaItems = tracks.map { path ->
            MediaItem.fromUri(path)
        }
        exoPlayer.setMediaItems(mediaItems)
        exoPlayer.prepare()
    }

    // Очистка ресурсов
    fun release() {
        exoPlayer.release()
        stopUpdatingProgress()
    }
}