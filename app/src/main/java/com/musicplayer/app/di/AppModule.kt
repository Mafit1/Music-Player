package com.musicplayer.app.di

import com.musicplayer.app.viewmodels.FullTrackListViewModel
import com.musicplayer.app.viewmodels.PlaylistsViewModel
import com.musicplayer.app.viewmodels.SettingsViewModel
import com.musicplayer.app.viewmodels.SinglePlaylistViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val appModule = module {
    viewModel<FullTrackListViewModel> {
        FullTrackListViewModel(
            addTrackToTrackList = get(),
            deleteTrackFromTrackList = get(),
            getAllTracksOrderedByNames = get()
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

    viewModel<SinglePlaylistViewModel> {
        SinglePlaylistViewModel(
            getTracksFromPlaylistOrderedByNames = get()
        )
    }
}