package com.example.domain.usecases

import com.example.domain.models.MusicTrackData
import com.example.domain.repositories.MusicTrackRepository

class AddTrackToTrackList(private val repository: MusicTrackRepository) {
    suspend fun execute(newTrack : MusicTrackData) {
        return repository.addMusicTrack(newTrack)
    }
}