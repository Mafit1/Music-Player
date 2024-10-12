package com.example.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity
data class PlaylistFull (
    @Embedded
    val info : PlaylistEntity,

    @Relation(
        parentColumn = "playlist_id",
        entity = MusicTrackEntity::class,
        entityColumn = "music_track_id",
        associateBy = Junction(PlaylistMusicTrackCrossRef::class)
    )
    val musicTracks: List<MusicTrackEntity>
)