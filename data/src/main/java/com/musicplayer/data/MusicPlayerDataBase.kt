package com.musicplayer.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.musicplayer.data.dao.MusicTrackDAO
import com.musicplayer.data.dao.PlaylistDAO
import com.musicplayer.data.models.MusicTrackEntity
import com.musicplayer.data.models.PlaylistEntity
import com.musicplayer.data.models.PlaylistMusicTrackCrossRef

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