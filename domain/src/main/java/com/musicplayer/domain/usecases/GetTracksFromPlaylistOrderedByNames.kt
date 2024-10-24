package com.musicplayer.domain.usecases

import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.repositories.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTracksFromPlaylistOrderedByNames(private val repository: PlaylistRepository) {
    fun execute(playlistId: Int?): Flow<List<MusicTrackData>> {
        return repository.getPlaylistWithTracksOrderedByNames(playlistId).map { it.musicTracks }
    }
}