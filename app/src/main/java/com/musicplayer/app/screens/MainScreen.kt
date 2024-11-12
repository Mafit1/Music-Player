package com.musicplayer.app.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.musicplayer.app.navigation.BottomBar
import com.musicplayer.app.navigation.BottomBarScreen
import com.musicplayer.app.navigation.BottomNavGraph
import com.musicplayer.app.screens.modules.PlayerBar
import com.musicplayer.app.viewmodels.FullTrackListViewModel
import com.musicplayer.app.viewmodels.PlaylistsViewModel
import com.musicplayer.app.viewmodels.SettingsViewModel
import com.musicplayer.app.viewmodels.SharedPlayerViewModel
import com.musicplayer.domain.models.PlaylistInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    fullTrackListViewModel: FullTrackListViewModel,
    playlistsViewModel: PlaylistsViewModel,
    settingsViewModel: SettingsViewModel,
    sharedPlayerViewModel: SharedPlayerViewModel
) {
    val navController = rememberNavController()
    val currentScreenTitle = remember { mutableStateOf(BottomBarScreen.BottomBarFullTrackList.title) }

    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments(),
        onResult = { uris ->
            fullTrackListViewModel.handleSelectedUris(context, uris)
        }
    )


    Scaffold(
        bottomBar = {
            BottomBar(navController = navController, currentScreen = currentScreenTitle)
        },
        topBar = {
            TopAppBar(
                title = {Text(currentScreenTitle.value)},
                actions = {
                    when (currentScreenTitle.value) {
                        BottomBarScreen.BottomBarFullTrackList.title -> {
                            IconButton({
                                launcher.launch(arrayOf("audio/mpeg"))
                            }) {
                                Icon(
                                    Icons.Default.Add,
                                    contentDescription = "Кнопка добавления трека"
                                )
                            }
                        }
                        BottomBarScreen.BottomBarPlaylists.title -> {
                            IconButton({
                                playlistsViewModel.addNewPlaylist(PlaylistInfo(name = "a", imageId = 1))
                            }) {
                                Icon(
                                    Icons.Default.Add,
                                    contentDescription = "Кнопка добавления плейлиста"
                                )
                            }
                        }
                    }
                }
            )
        }
    ) { innerPadding ->

        BottomNavGraph(
            navController = navController,
            paddingValues = innerPadding,
            fullTrackListViewModel = fullTrackListViewModel,
            playlistsViewModel = playlistsViewModel,
            settingsViewModel = settingsViewModel,
            sharedPlayerViewModel = sharedPlayerViewModel
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Bottom
        ) {
            PlayerBar(sharedPlayerViewModel)
        }
    }
}
