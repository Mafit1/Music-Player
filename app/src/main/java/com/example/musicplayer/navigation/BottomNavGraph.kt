package com.example.musicplayer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.musicplayer.screens.FullTrackListScreen
import com.example.musicplayer.screens.PlayListsScreen
import com.example.musicplayer.screens.SettingsScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.BottomBarFullTrackList.route
    ) {
        composable(route = BottomBarScreen.BottomBarFullTrackList.route) {
            FullTrackListScreen()
        }
        composable(route = BottomBarScreen.BottomBarPlaylists.route) {
            PlayListsScreen()
        }
        composable(route = BottomBarScreen.BottomBarSettings.route) {
            SettingsScreen()
        }
    }
}