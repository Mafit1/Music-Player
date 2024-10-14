package com.example.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.domain.models.MusicTrackData

@Entity(
    tableName = "music_track_table",
    foreignKeys = [
        ForeignKey(
            entity = PlaylistEntity::class,
            parentColumns = ["playlist_id"],
            childColumns = ["playlist_id"]
        )
    ]
)
data class MusicTrackEntity(
    val name: String,
    val author: String,
    val durationSec: Long,
    @ColumnInfo(name = "playlist_id")
    val playlistId: Int?,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "music_track_id")
    val id: Int? = null,
) {
    fun toDomain() : MusicTrackData {
        return MusicTrackData(
            id = id,
            name = name,
            author = author,
            durationSec = durationSec,
            playlistId = playlistId
        )
    }
}