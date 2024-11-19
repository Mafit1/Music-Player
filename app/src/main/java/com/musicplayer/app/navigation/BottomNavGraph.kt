package com.musicplayer.app.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.musicplayer.app.screens.FullTrackListScreen
import com.musicplayer.app.screens.PlayListsScreen
import com.musicplayer.app.screens.SettingsScreen
import com.musicplayer.app.screens.SinglePlayListScreen
import com.musicplayer.app.viewmodels.FullTrackListViewModel
import com.musicplayer.app.viewmodels.PlaylistsViewModel
import com.musicplayer.app.viewmodels.SettingsViewModel
import com.musicplayer.app.viewmodels.SharedPlayerViewModel
import com.musicplayer.app.viewmodels.SinglePlaylistViewModel

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    fullTrackListViewModel: FullTrackListViewModel,
    playlistsViewModel: PlaylistsViewModel,
    settingsViewModel: SettingsViewModel,
    singlePlaylistViewModel: SinglePlaylistViewModel,
    sharedPlayerViewModel: SharedPlayerViewModel
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.BottomBarFullTrackList.route
    ) {
        composable(route = BottomBarScreen.BottomBarFullTrackList.route) {
            FullTrackListScreen(fullTrackListViewModel, sharedPlayerViewModel, paddingValues)
        }
        composable(route = BottomBarScreen.BottomBarPlaylists.route) {
            PlayListsScreen(playlistsViewModel, paddingValues, navController)
        }
        composable(route = BottomBarScreen.BottomBarSettings.route) {
            SettingsScreen(settingsViewModel)
        }
        composable(route = "playlist_detail/{playlistId}") { backStackEntry ->
            val playlistId = backStackEntry.arguments?.getString("playlistId")?.toIntOrNull()
            if (playlistId != null) SinglePlayListScreen(
                playlistId = playlistId,
                singlePlaylistViewModel = singlePlaylistViewModel,
                sharedPlayerViewModel = sharedPlayerViewModel
            )
            else Text("Invalid Playlist ID")
        }
    }
}