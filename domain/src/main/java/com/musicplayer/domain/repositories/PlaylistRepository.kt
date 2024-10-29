package com.musicplayer.domain.repositories

import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.models.PlaylistWithTracks
import kotlinx.coroutines.flow.Flow

interface PlaylistRepository {

    suspend fun addPlaylist(newPlaylist: PlaylistInfo)

    suspend fun deletePlaylist(playlistToDelete: PlaylistInfo)

    fun getAllPlaylistsOrderedByNames(): Flow<List<PlaylistInfo>>

    fun getAllPlaylistsWithTracksOrderedByNames(): Flow<List<PlaylistWithTracks>>

    fun getPlaylistWithTracksOrderedByNames(playlist: PlaylistInfo): Flow<PlaylistWithTracks>

    fun getPlaylistById(playlistId: Int): PlaylistInfo

}