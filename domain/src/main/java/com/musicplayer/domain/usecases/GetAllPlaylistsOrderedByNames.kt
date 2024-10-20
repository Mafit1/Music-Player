package com.musicplayer.domain.usecases

import com.musicplayer.domain.models.PlaylistData
import com.musicplayer.domain.repositories.PlaylistRepository
import kotlinx.coroutines.flow.Flow

class GetAllPlaylistsOrderedByNames(private val repository: PlaylistRepository) {
    fun execute() : Flow<List<PlaylistData>> {
        return repository.getAllPlaylistsOrderedByNames()
    }
}