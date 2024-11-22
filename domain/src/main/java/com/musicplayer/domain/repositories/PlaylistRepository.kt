package com.musicplayer.domain.repositories

import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.models.PlaylistWithTracks
import kotlinx.coroutines.flow.Flow

interface PlaylistRepository {

    suspend fun addPlaylist(newPlaylist: PlaylistInfo)

    suspend fun deletePlaylist(playlistToDelete: PlaylistInfo)

    suspend fun deleteCrossRefsByPlaylistId(playlistId: Int)

    suspend fun removeTrackFromPlaylist(trackId: Int, playlistId: Int)

    fun getAllPlaylistsOrderedByNames(): Flow<List<PlaylistInfo>>

    fun getAllPlaylistsWithTracksOrderedByNames(): Flow<List<PlaylistWithTracks>>

    fun getPlaylistWithTracksOrderedByNames(playlistId: Int): Flow<PlaylistWithTracks>

    suspend fun getPlaylistById(playlistId: Int): PlaylistInfo

    suspend fun addTrackToPlaylist(trackId: Int, playlistId: Int)
}