package com.musicplayer.domain.usecases

import com.musicplayer.domain.models.PlaylistData
import com.musicplayer.domain.repositories.PlaylistRepository

class DeletePlaylist(private val repository: PlaylistRepository) {
    suspend fun execute(playlistToDelete: PlaylistData) {
        return repository.deletePlaylist(playlistToDelete)
    }
}