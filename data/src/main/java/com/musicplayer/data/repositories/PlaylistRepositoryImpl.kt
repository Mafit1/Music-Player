package com.musicplayer.data.repositories

import com.musicplayer.data.dao.PlaylistDAO
import com.musicplayer.data.models.PlaylistEntity
import com.musicplayer.domain.models.PlaylistData
import com.musicplayer.domain.repositories.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlaylistRepositoryImpl(
    private val playlistDAO: PlaylistDAO
) : PlaylistRepository {
    override suspend fun addPlaylist(newPlaylist: PlaylistData) = playlistDAO.upsertPlaylist(
        PlaylistEntity(
            id = newPlaylist.id,
            name = newPlaylist.name,
            imageId = newPlaylist.imageId // Мб поменять на ImageVector
        )
    )

    override suspend fun deletePlaylist(playlistToDelete: PlaylistData) = playlistDAO.deletePlaylist(
        PlaylistEntity(
            name = playlistToDelete.name,
            imageId = playlistToDelete.imageId,
            id = playlistToDelete.id
        )
    )

    override fun getAllPlaylistsOrderedByNames(): Flow<List<PlaylistData>> = playlistDAO.getAllPlaylistsOrderedByNames().map { list ->
        list.map { it.toDomain() }
    }
}