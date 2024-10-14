package com.example.musicplayer.screens

import androidx.compose.foundation.layout.PaddingValues
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
import com.example.domain.models.MusicTrackData
import com.example.domain.usecases.GetAllTracksOrderedByNames
import com.example.musicplayer.screens.modules.ItemMusicTrack
import com.example.musicplayer.viewmodels.FullTrackListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullTrackListScreen(
    viewModel: FullTrackListViewModel
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
                    Text("Мои треки")
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
        FullTrackList(
            paddingValues = innerPadding,
            listOfTracks = viewModel.getAllTracksOrderedByNames().collectAsState(initial = emptyList()).value
        )
    }
}

@Composable
private fun FullTrackList(
    paddingValues: PaddingValues,
    listOfTracks: List<MusicTrackData>
) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
    ) {
        itemsIndexed(
            listOfTracks
        ) { index, item ->
            ItemMusicTrack(musicTrackData = item, index = index + 1)
        }
    }
}