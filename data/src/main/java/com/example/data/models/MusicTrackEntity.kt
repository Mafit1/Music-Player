package com.example.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "music_track_table"
)
data class MusicTrackEntity(
    val name: String,
    val author: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
)