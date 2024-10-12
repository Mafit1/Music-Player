package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.dao.MusicTrackDAO
import com.example.data.dao.PlaylistDAO
import com.example.data.models.MusicTrackEntity
import com.example.data.models.PlaylistEntity
import com.example.data.models.PlaylistMusicTrackCrossRef

@Database(
    entities = [
        MusicTrackEntity::class,
        PlaylistEntity::class,
        PlaylistMusicTrackCrossRef::class
    ],
    version = 1
)
abstract class MusicPlayerDataBase : RoomDatabase() {
    abstract fun musicTrackDAO() : MusicTrackDAO
    abstract fun playlistDAO() : PlaylistDAO
}