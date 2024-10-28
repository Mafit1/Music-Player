package com.musicplayer.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.usecases.GetTracksFromPlaylistOrderedByNames
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

class SinglePlaylistViewModel(
    private val getTracksFromPlaylistOrderedByNames: GetTracksFromPlaylistOrderedByNames
) : ViewModel() {

    private val _currentPlaylist = MutableStateFlow<PlaylistInfo?>(null)
    val selectedPlaylist: StateFlow<PlaylistInfo?> = _currentPlaylist.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val tracks: StateFlow<List<MusicTrackData>> =
        _currentPlaylist.flatMapLatest { playlist ->
            if (playlist != null) getTracksFromPlaylistOrderedByNames.execute(1)
            else flowOf(emptyList())
        }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun selectPlaylist(playlist: PlaylistInfo) {
        _currentPlaylist.value = playlist
    }
}