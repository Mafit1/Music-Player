package com.musicplayer.domain.usecases

import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.repositories.PlaylistRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times

class GetPlaylistByIdTest {
    private val repository = mock<PlaylistRepository>()

    @Test
    fun `should return playlist as in repository by id`() {
        val useCase = GetPlaylistById(repository)
        val expected = PlaylistInfo(id = 1, name = "name", imageId = 0)
        val param = 1

        runBlocking {
            Mockito.`when`(repository.getPlaylistById(param)).thenReturn(expected)
            val actual = useCase.execute(param)

            Assertions.assertEquals(expected, actual)
            Mockito.verify(repository, times(1)).getPlaylistById(param)
        }
    }
}