package com.musicplayer.app.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.musicplayer.app.screens.FullTrackListScreen
import com.musicplayer.app.screens.PlayListsScreen
import com.musicplayer.app.screens.SettingsScreen
import com.musicplayer.app.viewmodels.FullTrackListViewModel
import com.musicplayer.app.viewmodels.PlaylistsViewModel
import com.musicplayer.app.viewmodels.SettingsViewModel

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    fullTrackListViewModel: FullTrackListViewModel,
    playlistsViewModel: PlaylistsViewModel,
    settingsViewModel: SettingsViewModel
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.BottomBarFullTrackList.route
    ) {
        composable(route = BottomBarScreen.BottomBarFullTrackList.route) {
            FullTrackListScreen(fullTrackListViewModel, paddingValues)
        }
        composable(route = BottomBarScreen.BottomBarPlaylists.route) {
            PlayListsScreen(playlistsViewModel, paddingValues)
        }
        composable(route = BottomBarScreen.BottomBarSettings.route) {
            SettingsScreen(settingsViewModel)
        }
    }
}