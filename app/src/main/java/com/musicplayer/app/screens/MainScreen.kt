package com.musicplayer.app.screens

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toFile
import androidx.navigation.compose.rememberNavController
import com.musicplayer.app.navigation.BottomBar
import com.musicplayer.app.navigation.BottomBarScreen
import com.musicplayer.app.navigation.BottomNavGraph
import com.musicplayer.app.viewmodels.FullTrackListViewModel
import com.musicplayer.app.viewmodels.PlaylistsViewModel
import com.musicplayer.app.viewmodels.SettingsViewModel
import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.models.PlaylistInfo
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    fullTrackListViewModel: FullTrackListViewModel,
    playlistsViewModel: PlaylistsViewModel,
    settingsViewModel: SettingsViewModel
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
                title = {Text(currentScreenTitle.value)}
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    when (currentScreenTitle.value) {
                        BottomBarScreen.BottomBarFullTrackList.title -> {
                            launcher.launch(arrayOf("audio/mpeg"))
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

private fun copyUriToFile(uri: Uri, context: Context): File? {
    val fileName = "selected_track.mp3"
    val destinationFile = File(context.filesDir, fileName)

    try {
        context.contentResolver.openInputStream(uri)?.use { inputStream ->
            destinationFile.outputStream().use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }

    return destinationFile
}
