package com.musicplayer.app.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.musicplayer.app.navigation.BottomBar
import com.musicplayer.app.navigation.BottomNavGraph
import com.musicplayer.app.viewmodels.FullTrackListViewModel
import com.musicplayer.app.viewmodels.PlaylistsViewModel
import com.musicplayer.app.viewmodels.SettingsViewModel

@Composable
fun MainScreen(
    fullTrackListViewModel: FullTrackListViewModel,
    playlistsViewModel: PlaylistsViewModel,
    settingsViewModel: SettingsViewModel
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { innerPadding ->
        innerPadding

        BottomNavGraph(
            navController = navController,
            fullTrackListViewModel = fullTrackListViewModel,
            playlistsViewModel = playlistsViewModel,
            settingsViewModel = settingsViewModel
        )
    }

}