package com.musicplayer.app.navigation

import com.example.musicplayer.R

sealed class BottomBarScreen (
    val route : String,
    val title : String,
    val icon : Int
) {
    data object BottomBarFullTrackList : BottomBarScreen(
        route = "fullTrackList",
        title = "Мои треки",
        icon = R.drawable.baseline_audiotrack_24
    )
    data object BottomBarPlaylists : BottomBarScreen(
        route = "playlists",
        title = "Мои плейлисты",
        icon = R.drawable.baseline_format_list_bulleted_24
    )
    data object BottomBarSettings : BottomBarScreen(
        route = "settings",
        title = "Настройки",
        icon = R.drawable.baseline_settings_24
    )
}