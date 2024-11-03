package com.musicplayer.app.di

import com.musicplayer.app.viewmodels.FullTrackListViewModel
import com.musicplayer.app.viewmodels.PlaylistsViewModel
import com.musicplayer.app.viewmodels.SettingsViewModel
import com.musicplayer.app.viewmodels.SinglePlaylistViewModel
import com.musicplayer.domain.models.PlaylistInfo
import org.koin.androidx.compose.koinViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val appModule = module {
    viewModel<FullTrackListViewModel> {
        FullTrackListViewModel(
            addTrackToTrackList = get(),
            deleteTrackFromTrackList = get(),
            getAllTracksOrderedByNames = get(),
            saveTrackFile = get()
        )
    }

    viewModel<PlaylistsViewModel> {
        PlaylistsViewModel(
            getAllPlaylistsOrderedByNames = get(),
            addNewPlaylist = get(),
            deletePlaylist = get()
        )
    }

    viewModel<SettingsViewModel> {
        SettingsViewModel(

        )
    }

    viewModel<SinglePlaylistViewModel> { (playlistId: Int) ->
        SinglePlaylistViewModel(
            playlistId = playlistId,
            getTracksFromPlaylistOrderedByNames = get(),
            addTrackToPlaylist = get(),
            getPlaylistById = get()
        )
    }
}