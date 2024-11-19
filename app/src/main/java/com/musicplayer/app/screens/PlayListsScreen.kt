package com.musicplayer.app.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.musicplayer.app.screens.modules.PlayList
import com.musicplayer.app.viewmodels.PlaylistsViewModel
import com.musicplayer.domain.models.PlaylistInfo

@OptIn(ExperimentalMaterial3Api::class)
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
            PlayList(
                playListInfo = item,
                onClick = {navController.navigate("playlist_detail/${item.id}")}
            )
        }
    }
}