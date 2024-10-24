package com.musicplayer.domain.usecases

import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.repositories.PlaylistRepository
import kotlinx.coroutines.flow.Flow

class GetAllPlaylistsOrderedByNames(private val repository: PlaylistRepository) {
    fun execute() : Flow<List<PlaylistInfo>> {
        return repository.getAllPlaylistsOrderedByNames()
    }
}