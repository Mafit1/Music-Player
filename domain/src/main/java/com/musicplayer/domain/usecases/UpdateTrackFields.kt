package com.musicplayer.domain.usecases

import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.repositories.MusicTrackRepository

class UpdateTrackFields(private val repository: MusicTrackRepository) {
    suspend fun execute(track: MusicTrackData, newName: String, newAuthor: String) {
        repository.updateTrackFields(track.id, newName, newAuthor)
    }
}