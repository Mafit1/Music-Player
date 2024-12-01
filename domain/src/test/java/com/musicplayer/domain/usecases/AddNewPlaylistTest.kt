package com.musicplayer.domain.usecases

import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.repositories.PlaylistRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times

class AddNewPlaylistTest {
    private val playlistRepository = mock<PlaylistRepository>()

    @Test
    fun `should invoke add method once`() {
        val useCase = AddNewPlaylist(playlistRepository)
        val param = PlaylistInfo(name = "playlist", imageId = 0)

        runBlocking {
            useCase.execute(param)
            Mockito.verify(playlistRepository, times(1)).addPlaylist(param)
        }
    }
}