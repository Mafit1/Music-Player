package com.musicplayer.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.musicplayer.data.models.PlaylistEntity
import com.musicplayer.data.models.PlaylistMusicTrackCrossRef
import com.musicplayer.data.models.PlaylistWithTracksEntity
import com.musicplayer.domain.models.PlaylistInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistDAO {
    @Upsert
    suspend fun upsertPlaylist(playlistEntity: PlaylistEntity)

    @Upsert
    suspend fun upsertPlaylistMusicTrackCrossRef(crossRef: PlaylistMusicTrackCrossRef)

    @Delete
    suspend fun deletePlaylist(playlistEntity: PlaylistEntity)

    @Query("DELETE FROM playlist_music_track_cross_ref WHERE playlist_id = :playlistId")
    suspend fun deleteCrossRefsByPlaylistId(playlistId: Int)

    @Query("DELETE FROM playlist_music_track_cross_ref WHERE music_track_id = :trackId AND playlist_id = :playlistId")
    suspend fun removeTrackFromPlaylist(trackId: Int, playlistId: Int)

    @Query("SELECT * FROM playlist_table ORDER BY name")
    fun getAllPlaylistsOrderedByNames(): Flow<List<PlaylistEntity>>

    @Transaction
    @Query("SELECT * FROM playlist_table ORDER BY playlist_id")
    fun getAllPlaylistsWithTracksOrderedByNames(): Flow<List<PlaylistWithTracksEntity>>

    @Transaction
    @Query("SELECT * FROM playlist_table WHERE playlist_id = :playlistId")
    fun getPlaylistWithTracksOrderedByNames(playlistId: Int): Flow<PlaylistWithTracksEntity?>

    @Transaction
    @Query("SELECT * FROM playlist_table WHERE playlist_id = :playlistId")
    fun getPlaylistById(playlistId: Int): PlaylistEntity
}