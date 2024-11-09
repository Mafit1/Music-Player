package com.musicplayer.app.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.musicplayer.app.screens.modules.ItemMusicTrack
import com.musicplayer.app.viewmodels.FullTrackListViewModel

@Composable
fun FullTrackListScreen(
    viewModel: FullTrackListViewModel,
    paddingValues: PaddingValues
) {
    val trackList = viewModel.getAllTracksOrderedByNames().collectAsState(initial = emptyList()).value

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
    ) {
        itemsIndexed(trackList) { index, item ->
            ItemMusicTrack(musicTrackData = item, index = index + 1)
        }
    }
}