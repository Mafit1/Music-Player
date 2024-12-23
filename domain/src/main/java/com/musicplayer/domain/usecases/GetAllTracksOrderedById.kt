package com.musicplayer.domain.usecases

import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.repositories.MusicTrackRepository
import kotlinx.coroutines.flow.Flow

class GetAllTracksOrderedById(private val repository: MusicTrackRepository) {
    fun execute() : Flow<List<MusicTrackData>> {
        return repository.getAllTracksOrderedById()
    }
}