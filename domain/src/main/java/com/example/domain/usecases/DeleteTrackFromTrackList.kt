package com.example.domain.usecases

import com.example.domain.models.MusicTrackData
import com.example.domain.repositories.MusicTrackRepository

class DeleteTrackFromTrackList(private val repository: MusicTrackRepository) {
    suspend fun execute(trackToDelete : MusicTrackData) {
        repository.deleteMusicTrack(trackToDelete)
    }
}