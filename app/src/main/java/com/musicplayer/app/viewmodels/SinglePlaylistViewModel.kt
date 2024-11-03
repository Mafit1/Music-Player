package com.musicplayer.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.usecases.AddTrackToPlaylist
import com.musicplayer.domain.usecases.GetPlaylistById
import com.musicplayer.domain.usecases.GetTracksFromPlaylistOrderedByNames
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SinglePlaylistViewModel(
    private val playlistId: Int,
    getTracksFromPlaylistOrderedByNames: GetTracksFromPlaylistOrderedByNames,
    private val addTrackToPlaylist: AddTrackToPlaylist,
    private val getPlaylistById: GetPlaylistById
) : ViewModel() {

    private val _playlist = MutableStateFlow<PlaylistInfo?>(null)
    val playlist: StateFlow<PlaylistInfo?> = _playlist

    init {
        loadPlaylist()
    }

    private fun loadPlaylist() {
        viewModelScope.launch {
            _playlist.value = getPlaylistById.execute(playlistId)
        }
    }

    val tracks: StateFlow<List<MusicTrackData>> =
        getTracksFromPlaylistOrderedByNames.execute(playlistId).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}