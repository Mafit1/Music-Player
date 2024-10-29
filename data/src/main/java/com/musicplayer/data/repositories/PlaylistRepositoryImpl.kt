package com.musicplayer.data.repositories

import com.musicplayer.data.dao.PlaylistDAO
import com.musicplayer.data.models.PlaylistEntity
import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.models.PlaylistWithTracks
import com.musicplayer.domain.repositories.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlaylistRepositoryImpl(
    private val playlistDAO: PlaylistDAO
) : PlaylistRepository {
    override suspend fun addPlaylist(newPlaylist: PlaylistInfo) = playlistDAO.upsertPlaylist(
        PlaylistEntity(
            id = newPlaylist.id,
            name = newPlaylist.name,
            imageId = newPlaylist.imageId // Мб поменять на ImageVector
        )
    )

    override suspend fun deletePlaylist(playlistToDelete: PlaylistInfo) = playlistDAO.deletePlaylist(
        PlaylistEntity(
            name = playlistToDelete.name,
            imageId = playlistToDelete.imageId,
            id = playlistToDelete.id
        )
    )

    override fun getAllPlaylistsOrderedByNames(): Flow<List<PlaylistInfo>> =
        playlistDAO.getAllPlaylistsOrderedByNames().map { playlist ->
        playlist.map { it.toDomain() }
    }

    override fun getAllPlaylistsWithTracksOrderedByNames(): Flow<List<PlaylistWithTracks>> =
        playlistDAO.getAllPlaylistsWithTracksOrderedByNames().map { playlist ->
        playlist.map { it.toDomain() }
    }

    override fun getPlaylistWithTracksOrderedByNames(playlist: PlaylistInfo): Flow<PlaylistWithTracks> =
        playlistDAO.getPlaylistWithTracksOrderedByNames(playlist.id).map { it.toDomain() }

    override fun getPlaylistById(playlistId: Int): PlaylistInfo =
        playlistDAO.getPlaylistById(playlistId).toDomain()
}