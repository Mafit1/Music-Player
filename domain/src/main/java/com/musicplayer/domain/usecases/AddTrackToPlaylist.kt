package com.musicplayer.domain.usecases

import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.repositories.MusicTrackRepository
import com.musicplayer.domain.repositories.PlaylistRepository

class AddTrackToPlaylist(private val repository: PlaylistRepository) {
    suspend fun execute(track: MusicTrackData, playlist: PlaylistInfo) {
        repository.addTrackToPlaylist(track.id, playlist.id)
    }
}