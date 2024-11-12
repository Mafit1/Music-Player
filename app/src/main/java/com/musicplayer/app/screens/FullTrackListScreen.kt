package com.musicplayer.app.screens

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.media3.common.Player
import com.musicplayer.app.screens.modules.ItemMusicTrack
import com.musicplayer.app.viewmodels.FullTrackListViewModel
import com.musicplayer.app.viewmodels.SharedPlayerViewModel

@Composable
fun FullTrackListScreen(
    fullTrackListViewModel: FullTrackListViewModel,
    sharedPlayerViewModel: SharedPlayerViewModel,
    paddingValues: PaddingValues
) {
    val trackList = fullTrackListViewModel.getAllTracksOrderedByNames().collectAsState(initial = emptyList()).value
    val currentTrackIndex = sharedPlayerViewModel.currentTrackIndex.collectAsState(initial = -1).value
    val currentPlayerState = sharedPlayerViewModel.currentPlayerState.collectAsState(initial = Player.STATE_READY).value

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .padding(bottom = 100.dp)
    ) {
        itemsIndexed(trackList) { index, item ->
            val trackIsPlaying = index == currentTrackIndex && currentPlayerState == Player.STATE_READY
            ItemMusicTrack(
                musicTrackData = item,
                index = index + 1,
                onClick = {
                    sharedPlayerViewModel.setPlaylist(trackList)
                    sharedPlayerViewModel.playTrackAt(index)
                    Log.d("myMusicPlayer", item.toString())
                },
                isPlaying = trackIsPlaying
            )
        }
    }
}