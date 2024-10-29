package com.musicplayer.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.usecases.AddTrackToPlaylist
import com.musicplayer.domain.usecases.GetTracksFromPlaylistOrderedByNames
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SinglePlaylistViewModel(
    private val playlist: PlaylistInfo,
    private val getTracksFromPlaylistOrderedByNames: GetTracksFromPlaylistOrderedByNames,
    private val addTrackToPlaylist: AddTrackToPlaylist
) : ViewModel() {

    val tracks: StateFlow<List<MusicTrackData>> =
        getTracksFromPlaylistOrderedByNames.execute(playlist).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addTrack(trackId: Int) {
        viewModelScope.launch {
            addTrackToPlaylist.execute(playlist, trackId)
        }
    }
//    private val _currentPlaylist = MutableStateFlow<PlaylistInfo?>(null)
//    val selectedPlaylist: StateFlow<PlaylistInfo?> = _currentPlaylist.asStateFlow()
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    val tracks: StateFlow<List<MusicTrackData>> =
//        _currentPlaylist.flatMapLatest { playlist ->
//            if (playlist != null) getTracksFromPlaylistOrderedByNames.execute(playlist)
//            else flowOf(emptyList())
//        }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
//
//    fun selectPlaylist(playlist: PlaylistInfo) {
//        _currentPlaylist.value = playlist
//    }
//
//    fun addTrackToPlaylist(musicTrack: MusicTrackData) = viewModelScope.launch {
//        addTrackToPlaylist.execute(_currentPlaylist.value, musicTrack)
//    }
}