package com.example.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.data.models.MusicTrackEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicTrackDAO {
    @Upsert
    suspend fun upsertTrack(musicTrackEntity: MusicTrackEntity)

    @Delete
    suspend fun deleteTrack(musicTrackEntity: MusicTrackEntity)

    @Query("SELECT * FROM music_track_table ORDER BY name")
    fun getAllTracksOrderedByNames(): Flow<List<MusicTrackEntity>>
}