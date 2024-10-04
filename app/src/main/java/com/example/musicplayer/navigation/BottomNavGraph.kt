package com.example.musicplayer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.musicplayer.screens.FullTrackListScreen
import com.example.musicplayer.screens.PlayListsScreen
import com.example.musicplayer.screens.SettingsScreen
import com.example.musicplayer.viewmodels.FullTrackListViewModel
import com.example.musicplayer.viewmodels.PlaylistsViewModel
import com.example.musicplayer.viewmodels.SettingsViewModel

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    fullTrackListViewModel: FullTrackListViewModel,
    playlistsViewModel: PlaylistsViewModel,
    settingsViewModel: SettingsViewModel
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.BottomBarFullTrackList.route
    ) {
        composable(route = BottomBarScreen.BottomBarFullTrackList.route) {
            FullTrackListScreen(fullTrackListViewModel)
        }
        composable(route = BottomBarScreen.BottomBarPlaylists.route) {
            PlayListsScreen(playlistsViewModel)
        }
        composable(route = BottomBarScreen.BottomBarSettings.route) {
            SettingsScreen(settingsViewModel)
        }
    }
}