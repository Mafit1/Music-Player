package com.musicplayer.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.musicplayer.domain.models.PlaylistData
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

    fun getAllPlaylistsOrderedByNames() : Flow<List<PlaylistData>> {
        return getAllPlaylistsOrderedByNames.execute()
    }

    fun addNewPlaylist(newPlaylist: PlaylistData) = viewModelScope.launch {
        addNewPlaylist.execute(newPlaylist)
    }

    fun deletePlaylist(playlistToDelete: PlaylistData) = viewModelScope.launch {
        deletePlaylist.execute(playlistToDelete)
    }
}