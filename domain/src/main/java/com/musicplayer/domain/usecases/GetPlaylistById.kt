package com.musicplayer.domain.usecases

import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.repositories.PlaylistRepository
import kotlinx.coroutines.flow.Flow

class GetPlaylistById(private val repository: PlaylistRepository) {
    suspend fun execute(playlistId: Int): PlaylistInfo {
        return repository.getPlaylistById(playlistId)
    }
}