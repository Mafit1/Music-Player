package com.example.musicplayer.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen (
    val route : String,
    val title : String,
    val icon : ImageVector
) {
    data object BottomBarFullTrackList : BottomBarScreen(
        route = "fullTrackList",
        title = "FullTrackList",
        icon = Icons.Default.Menu
    )
    data object BottomBarPlaylists : BottomBarScreen(
        route = "playlists",
        title = "Playlists",
        icon = Icons.Default.Star
    )
    data object BottomBarSettings : BottomBarScreen(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )
}