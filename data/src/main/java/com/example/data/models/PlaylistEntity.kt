package com.example.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlaylistEntity(
    val name: String,
    val image: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)
