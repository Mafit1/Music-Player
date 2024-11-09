package com.musicplayer.app.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.musicplayer.app.screens.modules.PlayList
import com.musicplayer.app.viewmodels.PlaylistsViewModel

@Composable
fun PlayListsScreen(
    viewModel: PlaylistsViewModel,
    paddingValues: PaddingValues,
    navController: NavHostController
) {
    val listOfPlaylists = viewModel.getAllPlaylistsOrderedByNames().collectAsState(initial = emptyList()).value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        itemsIndexed(listOfPlaylists) { _, item ->
            PlayList(playListInfo = item) {
                navController.navigate("playlist_detail/${item.id}")
            }
        }
    }
}