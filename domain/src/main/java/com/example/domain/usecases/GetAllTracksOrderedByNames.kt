package com.example.domain.usecases

import com.example.domain.models.MusicTrackData
import com.example.domain.repositories.MusicTrackRepository
import kotlinx.coroutines.flow.Flow

class GetAllTracksOrderedByNames(private val repository: MusicTrackRepository) {
    fun execute() : Flow<List<MusicTrackData>> {
        return repository.getAllTracksOrderedByNames()
    }
}