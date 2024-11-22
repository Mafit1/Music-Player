package com.musicplayer.app.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.musicplayer.app.screens.modules.PlayList
import com.musicplayer.app.screens.modules.PlaylistSettingsDialog
import com.musicplayer.app.viewmodels.PlaylistsViewModel
import com.musicplayer.domain.models.PlaylistInfo

@Composable
fun PlayListsScreen(
    viewModel: PlaylistsViewModel,
    paddingValues: PaddingValues,
    navController: NavHostController
) {
    val listOfPlaylists = viewModel.getAllPlaylistsOrderedByNames().collectAsState(initial = emptyList()).value

    var settingsDialog by remember { mutableStateOf(false) }

    var selectedPlaylist by remember { mutableStateOf(PlaylistInfo(name = "", imageId = -1)) }

    if (settingsDialog) {
        PlaylistSettingsDialog(
            playlist = selectedPlaylist,
            playlistsViewModel = viewModel,
            onDismissRequest = {
                settingsDialog = false
            }
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(bottom = 150.dp)
    ) {
        itemsIndexed(listOfPlaylists) { _, playlist ->
            PlayList(
                playListInfo = playlist,
                onClick = {navController.navigate("playlist_detail/${playlist.id}")},
                settingsOnClick = {
                    selectedPlaylist = playlist
                    settingsDialog = true
                }
            )
        }
    }
}