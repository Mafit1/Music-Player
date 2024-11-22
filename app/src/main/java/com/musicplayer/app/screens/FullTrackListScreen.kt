package com.musicplayer.app.screens

import androidx.compose.foundation.layout.PaddingValues
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
import androidx.media3.common.Player
import com.musicplayer.app.screens.modules.ItemMusicTrack
import com.musicplayer.app.screens.modules.TrackSettingsDialogFull
import com.musicplayer.app.viewmodels.FullTrackListViewModel
import com.musicplayer.app.viewmodels.SharedPlayerViewModel
import com.musicplayer.domain.models.MusicTrackData

@Composable
fun FullTrackListScreen(
    fullTrackListViewModel: FullTrackListViewModel,
    sharedPlayerViewModel: SharedPlayerViewModel,
    paddingValues: PaddingValues
) {
    val trackList = fullTrackListViewModel.getAllTracksOrderedByNames()
        .collectAsState(initial = emptyList()).value
    val currentTrackIndex =
        sharedPlayerViewModel.currentTrackIndex.collectAsState(initial = -1).value
    val currentPlayerState =
        sharedPlayerViewModel.currentPlayerState.collectAsState(initial = Player.STATE_READY).value

    var trackSettingsDialog by remember { mutableStateOf(false) }
    var selectedTrack by remember {
        mutableStateOf(MusicTrackData(name = "", author = "", duration = 0))
    }

    if (trackSettingsDialog) {
        TrackSettingsDialogFull(
            track = selectedTrack,
            fullTrackListViewModel = fullTrackListViewModel,
            onDismissRequest = {
                trackSettingsDialog = false
            }
        )
    }

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .padding(bottom = 150.dp)
    ) {
        itemsIndexed(trackList) { index, track ->
            val trackIsPlaying =
                index == currentTrackIndex && currentPlayerState == Player.STATE_READY
            ItemMusicTrack(
                musicTrackData = track,
                index = index + 1,
                onClick = {
                    sharedPlayerViewModel.setPlaylist(trackList)
                    sharedPlayerViewModel.playTrackAt(index)
                },
                settingsOnClick = {
                    trackSettingsDialog = true
                    selectedTrack = track
                },
                isPlaying = trackIsPlaying
            )
        }
    }
}