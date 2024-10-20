package com.musicplayer.domain.repositories

import com.musicplayer.domain.models.PlaylistData
import kotlinx.coroutines.flow.Flow

interface PlaylistRepository {
    suspend fun addPlaylist(newPlaylist: PlaylistData)

    suspend fun deletePlaylist(playlistToDelete: PlaylistData)

    fun getAllPlaylistsOrderedByNames() : Flow<List<PlaylistData>>
}