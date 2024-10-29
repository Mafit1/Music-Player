package com.musicplayer.domain.usecases

import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.repositories.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTracksFromPlaylistOrderedByNames(private val repository: PlaylistRepository) {
    fun execute(playlist: PlaylistInfo): Flow<List<MusicTrackData>> {
        return repository.getPlaylistWithTracksOrderedByNames(playlist).map { it.musicTracks }
    }
}