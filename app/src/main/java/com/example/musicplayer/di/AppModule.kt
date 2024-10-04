package com.example.musicplayer.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.musicplayer.viewmodels.FullTrackListViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val appModule = module {
    viewModel<FullTrackListViewModel> {
        FullTrackListViewModel(
            deleteTrackFromTrackList = get()
        )
    }
}