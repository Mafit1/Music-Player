package com.musicplayer.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.musicplayer.app.screens.MainScreen
import com.musicplayer.app.ui.theme.MusicPlayerTheme
import com.musicplayer.app.viewmodels.FullTrackListViewModel
import com.musicplayer.app.viewmodels.PlaylistsViewModel
import com.musicplayer.app.viewmodels.SettingsViewModel
import com.musicplayer.app.viewmodels.SinglePlaylistViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val fullTrackListViewModel by viewModel<FullTrackListViewModel>()
    private val playlistsViewModel by viewModel<PlaylistsViewModel>()
    private val settingsViewModel by viewModel<SettingsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusicPlayerTheme {
                MainScreen(
                    fullTrackListViewModel = fullTrackListViewModel,
                    playlistsViewModel = playlistsViewModel,
                    settingsViewModel = settingsViewModel
                )
            }
        }
    }
}



