package com.example.domain.usecases

import com.example.domain.models.MusicTrackData
import com.example.domain.repositories.MusicTrackRepository

class DeleteTrackFromTrackList(private val musicTrackRepository: MusicTrackRepository) {
    suspend fun execute(trackToDelete : MusicTrackData) {
        musicTrackRepository.deleteMusicTrack(trackToDelete)
    }
}