package com.musicplayer.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.musicplayer.data.models.MusicTrackEntity
import com.musicplayer.data.models.PlaylistEntity
import com.musicplayer.domain.models.MusicTrackData
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicTrackDAO {
    @Upsert
    suspend fun upsertTrack(musicTrackEntity: MusicTrackEntity)

    @Upsert
    suspend fun upsertTracks(musicTracksEntityList: List<MusicTrackEntity>)

    @Delete
    suspend fun deleteTrack(musicTrackEntity: MusicTrackEntity)

    @Query("UPDATE music_track_table SET name = :name, author = :author WHERE music_track_id = :trackId")
    suspend fun updateTrackFields(trackId: Int, name: String, author: String)

    @Query("SELECT * FROM music_track_table ORDER BY name")
    fun getAllTracksOrderedById(): Flow<List<MusicTrackEntity>>

    @Transaction
    @Query("SELECT * FROM music_track_table WHERE music_track_id = :trackId")
    fun getTrackById(trackId: Int): MusicTrackEntity
}