package com.musicplayer.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.musicplayer.data.models.MusicTrackEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicTrackDAO {
    @Upsert
    suspend fun upsertTrack(musicTrackEntity: MusicTrackEntity)

    @Upsert
    suspend fun upsertTracks(musicTracksEntityList: List<MusicTrackEntity>)

    @Delete
    suspend fun deleteTrack(musicTrackEntity: MusicTrackEntity)

    @Query("SELECT * FROM music_track_table ORDER BY name")
    fun getAllTracksOrderedByNames(): Flow<List<MusicTrackEntity>>
    // Мб неправильно
    @Query("UPDATE music_track_table SET playlist_id = :playlistId WHERE music_track_id = :musicTrackId")
    suspend fun updateTrackSetPlaylistId(playlistId: Int?, musicTrackId: Int?)
}