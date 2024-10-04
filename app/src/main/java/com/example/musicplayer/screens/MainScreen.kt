package com.example.musicplayer.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.musicplayer.navigation.BottomBar
import com.example.musicplayer.navigation.BottomNavGraph
import com.example.musicplayer.viewmodels.FullTrackListViewModel
import com.example.musicplayer.viewmodels.PlaylistsViewModel
import com.example.musicplayer.viewmodels.SettingsViewModel

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