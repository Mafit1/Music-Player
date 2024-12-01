package com.musicplayer.app.screens.modules

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.musicplayer.R
import com.musicplayer.app.screens.FullTrackListScreenObject
import com.musicplayer.app.screens.PlaylistsScreenObject
import com.musicplayer.app.screens.SettingsScreenObject

@Composable
fun BottomBar(
    navController: NavHostController,
    currentDestinationRoute: String?
) {
    androidx.compose.material3.BottomAppBar {
        NavigationBarItem(
            onClick = {
                navController.navigate(FullTrackListScreenObject)
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_audiotrack_24),
                    contentDescription = null
                )
            },
            selected = currentDestinationRoute == "com.musicplayer.app.screens.FullTrackListScreenObject"
        )
        NavigationBarItem(
            onClick = {
                navController.navigate(PlaylistsScreenObject)
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_format_list_bulleted_24),
                    contentDescription = null
                )
            },
            selected = currentDestinationRoute == "com.musicplayer.app.screens.PlaylistsScreenObject"
        )
        NavigationBarItem(
            onClick = {
                navController.navigate(SettingsScreenObject)
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_settings_24),
                    contentDescription = null
                )
            },
            selected = currentDestinationRoute == "com.musicplayer.app.screens.SettingsScreenObject"
        )
    }
}
