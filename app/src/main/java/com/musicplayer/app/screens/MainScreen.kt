package com.musicplayer.app.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.musicplayer.R
import com.musicplayer.app.navigation.BottomBar
import com.musicplayer.app.navigation.BottomBarScreen
import com.musicplayer.app.navigation.BottomNavGraph
import com.musicplayer.app.screens.modules.CreatePlaylistDialog
import com.musicplayer.app.screens.modules.PlayerBar
import com.musicplayer.app.viewmodels.FullTrackListViewModel
import com.musicplayer.app.viewmodels.PlaylistsViewModel
import com.musicplayer.app.viewmodels.SettingsViewModel
import com.musicplayer.app.viewmodels.SharedPlayerViewModel
import com.musicplayer.app.viewmodels.SinglePlaylistViewModel
import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.models.PlaylistInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    fullTrackListViewModel: FullTrackListViewModel,
    playlistsViewModel: PlaylistsViewModel,
    settingsViewModel: SettingsViewModel,
    singlePlaylistViewModel: SinglePlaylistViewModel,
    sharedPlayerViewModel: SharedPlayerViewModel
) {
    val navController = rememberNavController()
    val currentScreenTitle = remember { mutableStateOf(BottomBarScreen.BottomBarFullTrackList.title) }

    val context = LocalContext.current

    val isPlaying = sharedPlayerViewModel.isPlaying.collectAsState(initial = false).value

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments(),
        onResult = { uris ->
            val tracks = mutableListOf<MusicTrackData>()
            fullTrackListViewModel.handleSelectedUris(context, uris, tracks)
            if (isPlaying) sharedPlayerViewModel.addTracks(tracks)
        }
    )

    var createPlaylistDialog by remember { mutableStateOf(false) }

    if (createPlaylistDialog) {
        CreatePlaylistDialog(
            onConfirmRequest = { playlist ->
                playlistsViewModel.addNewPlaylist(playlist)
                createPlaylistDialog = false
            },
            onDismissRequest = {
                createPlaylistDialog = false
            }
        )
    }

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController, currentScreen = currentScreenTitle)
        },
        topBar = {
            when (currentScreenTitle.value) {
                BottomBarScreen.BottomBarFullTrackList.title -> {
                    TopAppBar(
                        title = {Text("Моя медиатека")},
                        actions = {
                            IconButton({
                                launcher.launch(arrayOf("audio/mpeg"))
                            }) {
                                Icon(
                                    Icons.Default.Add,
                                    contentDescription = "Кнопка добавления трека"
                                )
                            }
                        }
                    )
                }
                BottomBarScreen.BottomBarPlaylists.title -> {
                    TopAppBar(
                        title = {Text("Мои плейлисты")},
                        actions = {
                            IconButton(onClick = {
                                createPlaylistDialog = true
                            }) {
                                Icon(
                                    Icons.Default.Add,
                                    contentDescription = "Кнопка добавления плейлиста"
                                )
                            }
                        }
                    )
                }
                BottomBarScreen.BottomBarSettings.title -> {
                    TopAppBar(
                        title = {Text("Настройки")},
                    )
                }
            }
        }
    ) { innerPadding ->

        BottomNavGraph(
            navController = navController,
            paddingValues = innerPadding,
            fullTrackListViewModel = fullTrackListViewModel,
            playlistsViewModel = playlistsViewModel,
            settingsViewModel = settingsViewModel,
            singlePlaylistViewModel = singlePlaylistViewModel,
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
