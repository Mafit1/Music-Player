package com.example.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.PlaylistData

@Entity(
    tableName = "playlist_table"
)
data class PlaylistEntity(
    val name: String,
    val imageId: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "playlist_id")
    val id: Int? = null
) {
    fun toDomain() : PlaylistData {
        return PlaylistData(
            id = id,
            name = name,
            imageId = imageId
        )
    }
}
