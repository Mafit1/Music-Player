package com.musicplayer.domain.usecases

import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.repositories.MusicTrackRepository

class AddTrackToTrackList(private val repository: MusicTrackRepository) {
    suspend fun execute(newTrack : MusicTrackData) {
        return repository.addMusicTrack(newTrack)
    }
}