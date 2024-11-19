package com.musicplayer.domain.usecases

import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.repositories.PlaylistRepository

class RemoveTrackFromPlaylist(private val repository: PlaylistRepository) {
    suspend fun execute(track: MusicTrackData, playlist: PlaylistInfo) {
        return repository.removeTrackFromPlaylist(track.id, playlist.id)
    }
}