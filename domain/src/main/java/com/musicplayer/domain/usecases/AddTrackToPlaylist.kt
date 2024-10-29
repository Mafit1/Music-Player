package com.musicplayer.domain.usecases

import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.repositories.MusicTrackRepository

class AddTrackToPlaylist(private val repository: MusicTrackRepository) {
    suspend fun execute(playlist: PlaylistInfo, musicTrackId: Int) {
        repository.setPlaylistToTrack(playlist, musicTrackId)
    }
}