package com.musicplayer.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.models.PlaylistInfo

@Entity(
    tableName = "playlist_table"
)
data class PlaylistEntity(
    val name: String,
    val imageId: Int,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "playlist_id")
    val id: Int = 0
) {
    fun toDomain() : PlaylistInfo {
        return PlaylistInfo(
            id = id,
            name = name,
            imageId = imageId,
        )
    }
}
