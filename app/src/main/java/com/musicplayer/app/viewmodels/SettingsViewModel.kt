package com.musicplayer.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicplayer.R
import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.usecases.AddNewPlaylist
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val addNewPlaylist: AddNewPlaylist
) : ViewModel() {
    fun createTestPlaylists() = viewModelScope.launch {
        for(i in 1..5) {
            addNewPlaylist.execute(
                PlaylistInfo(name = "Плейлист $i", imageId = R.drawable.playlist_cover)
            )
        }
    }
}