package com.example.musicplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.musicplayer.screens.MainScreen
import com.example.musicplayer.ui.theme.MusicPlayerTheme
import com.example.musicplayer.viewmodels.FullTrackListViewModel
import com.example.musicplayer.viewmodels.PlaylistsViewModel
import com.example.musicplayer.viewmodels.SettingsViewModel
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



