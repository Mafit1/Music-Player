package com.musicplayer.app.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
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
import kotlinx.serialization.Serializable

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

    val currentDestination: State<NavBackStackEntry?> = navController.currentBackStackEntryAsState()

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
            BottomBar(navController = navController, currentDestinationRoute = currentDestination.value?.destination?.route)
        },
        topBar = {
            when (currentDestination.value?.destination?.route) {
                "com.musicplayer.app.screens.FullTrackListScreenObject" -> {
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
                "com.musicplayer.app.screens.PlaylistsScreenObject" -> {
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
                "com.musicplayer.app.screens.SettingsScreenObject" -> {
                    TopAppBar(
                        title = {Text("Настройки")},
                    )
                }
                else -> {}
            }
        }
    ) { innerPadding ->

        NavHost(
            startDestination = FullTrackListScreenObject,
            navController = navController
        ) {
            composable<FullTrackListScreenObject> {
                FullTrackListScreen(
                    fullTrackListViewModel = fullTrackListViewModel,
                    sharedPlayerViewModel = sharedPlayerViewModel,
                    paddingValues = innerPadding
                )
            }
            composable<PlaylistsScreenObject> {
                PlayListsScreen(
                    viewModel = playlistsViewModel,
                    paddingValues = innerPadding,
                    onPlaylistClick = {
                        navController.navigate(SinglePlaylistScreenObject)
                    }
                )
            }
            composable<SettingsScreenObject> {
                SettingsScreen(
                    viewModel = settingsViewModel,
                    paddingValues = innerPadding
                )
            }
            composable<SinglePlaylistScreenObject> {
                SinglePlayListScreen(
                    playlistId = playlistsViewModel.currentPlaylistId,
                    singlePlaylistViewModel = singlePlaylistViewModel,
                    sharedPlayerViewModel = sharedPlayerViewModel
                )
            }
        }

//        BottomNavGraph(
//            navController = navController,
//            paddingValues = innerPadding,
//            fullTrackListViewModel = fullTrackListViewModel,
//            playlistsViewModel = playlistsViewModel,
//            settingsViewModel = settingsViewModel,
//            singlePlaylistViewModel = singlePlaylistViewModel,
//            sharedPlayerViewModel = sharedPlayerViewModel
//        )

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

@Serializable
object FullTrackListScreenObject

@Serializable
object PlaylistsScreenObject

@Serializable
object SettingsScreenObject

@Serializable
object SinglePlaylistScreenObject