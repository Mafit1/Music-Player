package com.musicplayer.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.usecases.AddNewPlaylist
import com.musicplayer.domain.usecases.DeletePlaylist
import com.musicplayer.domain.usecases.GetAllPlaylistsOrderedByNames
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PlaylistsViewModel(
    private val getAllPlaylistsOrderedByNames: GetAllPlaylistsOrderedByNames,
    private val addNewPlaylist: AddNewPlaylist,
    private val deletePlaylist: DeletePlaylist
) : ViewModel() {

    fun getAllPlaylistsOrderedByNames() : Flow<List<PlaylistInfo>> {
        return getAllPlaylistsOrderedByNames.execute()
    }

    fun addNewPlaylist(newPlaylist: PlaylistInfo) = viewModelScope.launch {
        addNewPlaylist.execute(newPlaylist)
    }

    fun deletePlaylist(playlistToDelete: PlaylistInfo) = viewModelScope.launch {
        deletePlaylist.execute(playlistToDelete)
    }
}