package com.example.domain.usecases

import com.example.domain.repositories.MusicTrackRepository

class GetAllTracksOrderedByNames(private val repository: MusicTrackRepository) {
    fun execute() {
        repository.getAllTracksOrderedByNames()
    }
}