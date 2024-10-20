package com.musicplayer.domain.usecases

import com.musicplayer.domain.models.PlaylistData
import com.musicplayer.domain.repositories.PlaylistRepository

class AddNewPlaylist(private val repository: PlaylistRepository) {
    suspend fun execute(newPlaylist: PlaylistData) {
        repository.addPlaylist(newPlaylist)
    }
}