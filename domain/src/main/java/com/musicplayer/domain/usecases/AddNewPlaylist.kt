package com.musicplayer.domain.usecases

import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.repositories.PlaylistRepository

class AddNewPlaylist(private val repository: PlaylistRepository) {
    suspend fun execute(newPlaylist: PlaylistInfo) {
        repository.addPlaylist(newPlaylist)
    }
}