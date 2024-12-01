package com.musicplayer.domain.usecases

import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.domain.repositories.PlaylistRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times

class AddTrackToPlaylistTest {
    private val repository = mock<PlaylistRepository>()

    @Test
    fun `should invoke add method once`() {
        val useCase = AddTrackToPlaylist(repository)

        val param1 = MusicTrackData(name = "a", author = "b", duration = 1)
        val param2 = PlaylistInfo(name = "p", imageId = 0)

        runBlocking {
            useCase.execute(param1, param2)
            Mockito.verify(repository, times(1)).addTrackToPlaylist(param1.id, param2.id)
        }
    }
}