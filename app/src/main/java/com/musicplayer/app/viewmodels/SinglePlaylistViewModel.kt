package com.musicplayer.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.usecases.AddTrackToPlaylist
import com.musicplayer.domain.usecases.GetPlaylistById
import com.musicplayer.domain.usecases.GetTracksFromPlaylistOrderedByNames
import com.musicplayer.domain.usecases.RemoveTrackFromPlaylist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SinglePlaylistViewModel(
    getTracksFromPlaylistOrderedByNames: GetTracksFromPlaylistOrderedByNames,
    private val getPlaylistById: GetPlaylistById,
    private val removeTrackFromPlaylist: RemoveTrackFromPlaylist
) : ViewModel() {

    private val _playlist = MutableStateFlow<PlaylistInfo?>(null)
    val playlist: StateFlow<PlaylistInfo?> = _playlist

    @OptIn(ExperimentalCoroutinesApi::class)
    val tracks: StateFlow<List<MusicTrackData>> = _playlist
        .flatMapLatest { playlistInfo ->
            playlistInfo?.let {
                getTracksFromPlaylistOrderedByNames.execute(it.id)
            } ?: flowOf(emptyList())
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun setPlaylist(playlistId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _playlist.value = getPlaylistById.execute(playlistId)
        }
    }

    fun removeTrackFromPlaylist(track: MusicTrackData) =
        viewModelScope.launch {
            _playlist.value?.let { removeTrackFromPlaylist.execute(track, it) }
        }
}