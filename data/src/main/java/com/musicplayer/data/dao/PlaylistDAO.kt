package com.musicplayer.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.musicplayer.data.models.PlaylistEntity
import com.musicplayer.data.models.PlaylistWithTracksEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistDAO {
    @Upsert
    suspend fun upsertPlaylist(playlistEntity: PlaylistEntity)

    @Delete
    suspend fun deletePlaylist(playlistEntity: PlaylistEntity)

    @Query("SELECT * FROM playlist_table ORDER BY name")
    fun getAllPlaylistsOrderedByNames(): Flow<List<PlaylistEntity>>

    @Transaction
    @Query("SELECT * FROM playlist_table ORDER BY name")
    fun getAllPlaylistsWithTracksOrderedByNames(): Flow<List<PlaylistWithTracksEntity>>

    @Transaction
    @Query("SELECT * FROM playlist_table WHERE playlist_id = :playlistId ORDER BY name")
    fun getPlaylistWithTracksOrderedByNames(playlistId: Int?) : Flow<PlaylistWithTracksEntity>
}