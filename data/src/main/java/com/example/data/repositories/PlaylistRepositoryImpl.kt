package com.example.data.repositories

import com.example.data.dao.PlaylistDAO
import com.example.domain.models.PlaylistData
import com.example.domain.repositories.PlaylistRepository
import kotlinx.coroutines.flow.Flow

class PlaylistRepositoryImpl(
    playlistDAO: PlaylistDAO
) : PlaylistRepository {
    override suspend fun addPlaylist(newPlaylist: PlaylistData) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePlaylist(playlistToDelete: PlaylistData) {
        TODO("Not yet implemented")
    }

    override fun getAllPlaylistsOrderedByNames(): Flow<List<PlaylistData>> {
        TODO("Not yet implemented")
    }
}