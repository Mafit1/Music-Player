package com.musicplayer.domain.usecases

import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.repositories.PlaylistRepository

class GetPlaylistById(private val repository: PlaylistRepository) {
    fun execute(playlistId: Int): PlaylistInfo {
        return repository.getPlaylistById(playlistId)
    }
}