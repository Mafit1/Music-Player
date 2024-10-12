package com.example.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "playlist_music_track_cross_ref",
    primaryKeys = ["music_track_id", "playlist_id"],
    foreignKeys = [
        ForeignKey(
            entity = MusicTrackEntity::class,
            parentColumns = ["music_track_id"],
            childColumns = ["music_track_id"]
        ),
        ForeignKey(
            entity = PlaylistEntity::class,
            parentColumns = ["playlist_id"],
            childColumns = ["playlist_id"]
        )
    ]
)
data class PlaylistMusicTrackCrossRef(
    @ColumnInfo(name = "music_track_id")
    val trackId: Int,
    @ColumnInfo(name = "playlist_id")
    val playlistId: Int
)
