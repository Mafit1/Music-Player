package com.example.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.dao.MusicTrackDAO
import com.example.data.models.MusicTrackEntity
import com.example.data.models.PlaylistEntity

@Database(
    entities = [
        MusicTrackEntity::class,
        PlaylistEntity::class
    ],
    version = 1
)
abstract class MusicPlayerDataBase : RoomDatabase() {
    abstract val musicTrackDAO : MusicTrackDAO
}