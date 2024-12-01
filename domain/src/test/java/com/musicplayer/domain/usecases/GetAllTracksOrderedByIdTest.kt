package com.musicplayer.domain.usecases

import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.repositories.MusicTrackRepository
import kotlinx.coroutines.flow.flowOf
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times

class GetAllTracksOrderedByIdTest {

    private val musicTrackRepository = mock<MusicTrackRepository>()

    @Test
    fun `should return same tracks as in repository, ordered by id`() {
        val useCase = GetAllTracksOrderedById(musicTrackRepository)
        val expected = flowOf(
            listOf(
                MusicTrackData(name = "name1", author = "author1", duration = 1),
                MusicTrackData(name = "name2", author = "author2", duration = 1),
                MusicTrackData(name = "name3", author = "author3", duration = 1)
            )
        )

        Mockito.`when`(musicTrackRepository.getAllTracksOrderedById()).thenReturn(expected)

        val actual = useCase.execute()

        Assertions.assertEquals(expected, actual)
        Mockito.verify(musicTrackRepository, times(1)).getAllTracksOrderedById()
    }
}