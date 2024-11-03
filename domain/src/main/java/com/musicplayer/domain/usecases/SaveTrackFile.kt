package com.musicplayer.domain.usecases

import com.musicplayer.domain.repositories.MusicTrackRepository
import java.io.File

class SaveTrackFile(private val repository: MusicTrackRepository) {
    suspend fun execute(file: File) {
        return repository.saveTrackFile(file)
    }
}