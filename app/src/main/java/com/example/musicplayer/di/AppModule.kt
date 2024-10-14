package com.example.musicplayer.di

import com.example.musicplayer.viewmodels.FullTrackListViewModel
import com.example.musicplayer.viewmodels.PlaylistsViewModel
import com.example.musicplayer.viewmodels.SettingsViewModel
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

        )
    }

    viewModel<SettingsViewModel> {
        SettingsViewModel(

        )
    }
}