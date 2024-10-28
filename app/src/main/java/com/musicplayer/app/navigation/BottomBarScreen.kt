package com.musicplayer.app.navigation

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
        title = "Мои треки",
        icon = Icons.Default.Menu
    )
    data object BottomBarPlaylists : BottomBarScreen(
        route = "playlists",
        title = "Мои плейлисты",
        icon = Icons.Default.Star
    )
    data object BottomBarSettings : BottomBarScreen(
        route = "settings",
        title = "Настройки",
        icon = Icons.Default.Settings
    )
}