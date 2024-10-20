package com.musicplayer.domain.usecases

import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.repositories.MusicTrackRepository

class DeleteTrackFromTrackList(private val repository: MusicTrackRepository) {
    suspend fun execute(trackToDelete : MusicTrackData) {
        return repository.deleteMusicTrack(trackToDelete)
    }
}