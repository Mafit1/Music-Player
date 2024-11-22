package com.musicplayer.domain.usecases

import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.repositories.PlaylistRepository

class DeletePlaylist(private val repository: PlaylistRepository) {
    suspend fun execute(playlistToDelete: PlaylistInfo) {
        repository.deletePlaylist(playlistToDelete)
    }
}