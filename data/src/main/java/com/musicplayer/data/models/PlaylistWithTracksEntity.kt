package com.musicplayer.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.musicplayer.domain.models.PlaylistWithTracks

@Entity
data class PlaylistWithTracksEntity (
    @Embedded
    val info : PlaylistEntity,

    @Relation(
        parentColumn = "playlist_id",
        entity = MusicTrackEntity::class,
        entityColumn = "music_track_id",
        associateBy = Junction(PlaylistMusicTrackCrossRef::class)
    )
    val musicTracks: List<MusicTrackEntity>
) {
    fun toDomain() : PlaylistWithTracks {
        return PlaylistWithTracks(
            playlistInfo = info.toDomain(),
            musicTracks = musicTracks.map { it.toDomain() }
        )
    }
}