package com.musicplayer.domain.usecases

import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.repositories.PlaylistRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times

class DeletePlaylistTest {
    private val repository = mock<PlaylistRepository>()

    @Test
    fun `should invoke delete method once`() {
        val useCase = DeletePlaylist(repository)
        val param = PlaylistInfo(name = "a", imageId = 0)

        runBlocking {
            useCase.execute(param)
            Mockito.verify(repository, times(1)).deletePlaylist(param)
        }
    }
}