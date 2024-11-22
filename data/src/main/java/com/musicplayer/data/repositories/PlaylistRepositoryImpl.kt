package com.musicplayer.data.repositories

import com.musicplayer.data.dao.PlaylistDAO
import com.musicplayer.data.models.PlaylistEntity
import com.musicplayer.data.models.PlaylistMusicTrackCrossRef
import com.musicplayer.data.models.PlaylistWithTracksEntity
import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.models.PlaylistWithTracks
import com.musicplayer.domain.repositories.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map

class PlaylistRepositoryImpl(
    private val playlistDAO: PlaylistDAO
) : PlaylistRepository {
    override suspend fun addPlaylist(newPlaylist: PlaylistInfo) = playlistDAO.upsertPlaylist(
        PlaylistEntity(
            name = newPlaylist.name,
            imageId = newPlaylist.imageId
        )
    )

    override suspend fun deletePlaylist(playlistToDelete: PlaylistInfo) = playlistDAO.deletePlaylist(
        PlaylistEntity(
            name = playlistToDelete.name,
            imageId = playlistToDelete.imageId,
            id = playlistToDelete.id
        )
    )

    override suspend fun deleteCrossRefsByPlaylistId(playlistId: Int) =
        playlistDAO.deleteCrossRefsByPlaylistId(playlistId)

    override suspend fun removeTrackFromPlaylist(trackId: Int, playlistId: Int) =
        playlistDAO.removeTrackFromPlaylist(trackId, playlistId)

    override fun getAllPlaylistsOrderedByNames(): Flow<List<PlaylistInfo>> =
        playlistDAO.getAllPlaylistsOrderedByNames().map { playlist ->
            playlist.map { it.toDomain() }
        }

    override fun getAllPlaylistsWithTracksOrderedByNames(): Flow<List<PlaylistWithTracks>> =
        playlistDAO.getAllPlaylistsWithTracksOrderedByNames().map { playlistsWithTracks ->
            playlistsWithTracks.map { it.toDomain() }
        }

    override fun getPlaylistWithTracksOrderedByNames(playlistId: Int): Flow<PlaylistWithTracks> =
        playlistDAO.getPlaylistWithTracksOrderedByNames(playlistId).map { playlistWithTracks ->
            playlistWithTracks?.toDomain() ?: PlaylistWithTracks(
                PlaylistInfo(name = "", imageId = -1),
                emptyList()
            )
        }

    override suspend fun getPlaylistById(playlistId: Int): PlaylistInfo =
        playlistDAO.getPlaylistById(playlistId).toDomain()

    override suspend fun addTrackToPlaylist(trackId: Int, playlistId: Int) {
        playlistDAO.upsertPlaylistMusicTrackCrossRef(
            PlaylistMusicTrackCrossRef(trackId = trackId, playlistId = playlistId)
        )
    }
}