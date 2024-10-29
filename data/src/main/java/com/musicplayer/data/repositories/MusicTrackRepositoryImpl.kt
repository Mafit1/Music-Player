package com.musicplayer.data.repositories

import com.musicplayer.data.dao.MusicTrackDAO
import com.musicplayer.data.models.MusicTrackEntity
import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.repositories.MusicTrackRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MusicTrackRepositoryImpl(
    private val musicTrackDAO: MusicTrackDAO
) : MusicTrackRepository {
    override suspend fun addMusicTrack(newTrack: MusicTrackData) = musicTrackDAO.upsertTrack(
        MusicTrackEntity(
            id = newTrack.id,
            name = newTrack.name,
            author = newTrack.author,
            durationSec = newTrack.durationSec,
            playlistId = newTrack.playlistId
        )
    )

    override suspend fun deleteMusicTrack(trackToDelete: MusicTrackData) = musicTrackDAO.deleteTrack(
        MusicTrackEntity(
            id = trackToDelete.id,
            name = trackToDelete.name,
            author = trackToDelete.author,
            durationSec = trackToDelete.durationSec,
            playlistId = trackToDelete.playlistId
        )
    )

    override fun getAllTracksOrderedByNames(): Flow<List<MusicTrackData>> = musicTrackDAO.getAllTracksOrderedByNames().map { list ->
        list.map { it.toDomain() }
    }

    override suspend fun setPlaylistToTrack(playlist: PlaylistInfo, musicTrackId: Int) = musicTrackDAO.updateTrackSetPlaylistId(playlist.id, musicTrackId)
}