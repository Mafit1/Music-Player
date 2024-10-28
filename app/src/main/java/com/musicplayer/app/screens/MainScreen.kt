package com.musicplayer.app.screens

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.musicplayer.app.navigation.BottomBar
import com.musicplayer.app.navigation.BottomBarScreen
import com.musicplayer.app.navigation.BottomNavGraph
import com.musicplayer.app.viewmodels.FullTrackListViewModel
import com.musicplayer.app.viewmodels.PlaylistsViewModel
import com.musicplayer.app.viewmodels.SettingsViewModel
import com.musicplayer.app.viewmodels.SinglePlaylistViewModel
import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.models.PlaylistInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    fullTrackListViewModel: FullTrackListViewModel,
    playlistsViewModel: PlaylistsViewModel,
    settingsViewModel: SettingsViewModel,
    singlePlaylistViewModel: SinglePlaylistViewModel
) {
    val navController = rememberNavController()
    val currentScreenTitle = remember { mutableStateOf(BottomBarScreen.BottomBarFullTrackList.title) }

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController, currentScreen = currentScreenTitle)
        },
        topBar = {
            TopAppBar(
                title = {Text(currentScreenTitle.value)}
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    when (currentScreenTitle.value) {
                        BottomBarScreen.BottomBarFullTrackList.title -> {
                            fullTrackListViewModel.addTrackToTrackList(MusicTrackData(name = "a", author = "a", durationSec = 1))
                        }
                        BottomBarScreen.BottomBarPlaylists.title -> {
                            playlistsViewModel.addNewPlaylist(PlaylistInfo(name = "a", imageId = 1))
                        }
                    }
                }
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Кнопка добавления трека/плейлиста"
                )
            }
        }
    ) { innerPadding ->

        BottomNavGraph(
            navController = navController,
            paddingValues = innerPadding,
            fullTrackListViewModel = fullTrackListViewModel,
            playlistsViewModel = playlistsViewModel,
            settingsViewModel = settingsViewModel
        )
    }
}
