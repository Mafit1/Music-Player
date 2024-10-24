package com.musicplayer.app.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.musicplayer.domain.models.PlaylistInfo
import com.musicplayer.app.screens.modules.PlayList
import com.musicplayer.app.viewmodels.PlaylistsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayListsScreen(
    viewModel: PlaylistsViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = Color.Gray,
                    navigationIconContentColor = Color.Black,
                    scrolledContainerColor = Color.Gray,
                    titleContentColor = Color.Black,
                    actionIconContentColor = Color.Black
                ),
                title = {
                    Text("Мои плейлисты")
                },
                actions = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        viewModel.addNewPlaylist(PlaylistInfo(1, "name", "imageId"))
        viewModel.addNewPlaylist(PlaylistInfo(2, "name2", "imageId2"))
        viewModel.deletePlaylist(PlaylistInfo(2, "name2", "imageId2"))
        PlayListsList(
            paddingValues = innerPadding,
            listOfPlaylists = viewModel.getAllPlaylistsOrderedByNames().collectAsState(initial = emptyList()).value
        )
    }
}


@Composable
private fun PlayListsList(
    paddingValues: PaddingValues,
    listOfPlaylists: List<PlaylistInfo>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        itemsIndexed(listOfPlaylists) { _, item ->
            PlayList(playListInfo = item)
        }
    }
}