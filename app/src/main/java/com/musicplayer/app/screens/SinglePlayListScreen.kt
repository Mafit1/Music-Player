package com.musicplayer.app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.Player
import com.example.musicplayer.R
import com.musicplayer.app.screens.modules.ItemMusicTrack
import com.musicplayer.app.screens.modules.TrackSettingsDialogPlaylist
import com.musicplayer.app.viewmodels.SharedPlayerViewModel
import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.app.viewmodels.SinglePlaylistViewModel
import com.musicplayer.domain.models.PlaylistInfo


@Composable
fun SinglePlayListScreen(
    playlistId: Int?,
    singlePlaylistViewModel: SinglePlaylistViewModel,
    sharedPlayerViewModel: SharedPlayerViewModel
) {
    LaunchedEffect(Unit) {
        if (playlistId != null) singlePlaylistViewModel.setPlaylist(playlistId)
    }

    val playlist by singlePlaylistViewModel.playlist.collectAsState()

    val tracks by singlePlaylistViewModel.tracks.collectAsState()

    val currentTrackIndex =
        sharedPlayerViewModel.currentTrackIndex.collectAsState(initial = -1).value
    val currentPlayerState =
        sharedPlayerViewModel.currentPlayerState.collectAsState(initial = Player.STATE_READY).value

    var trackSettingsDialog by remember { mutableStateOf(false) }
    var selectedTrack by remember {
        mutableStateOf(MusicTrackData(name = "", author = "", duration = 0))
    }

    if (trackSettingsDialog) {
        TrackSettingsDialogPlaylist(
            track = selectedTrack,
            singlePlaylistViewModel = singlePlaylistViewModel,
            onDismissRequest = {
                trackSettingsDialog = false
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        PlayListInformation(
            playlistInfo = playlist ?: PlaylistInfo(
                name = "",
                imageId = R.drawable.ic_launcher_foreground
            )
        )
        LazyColumn {
            itemsIndexed(tracks) { index, track ->
                val trackIsPlaying =
                    index == currentTrackIndex && currentPlayerState == Player.STATE_READY
                ItemMusicTrack(
                    musicTrackData = track,
                    index = index + 1,
                    onClick = {
                        sharedPlayerViewModel.setPlaylist(tracks)
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
}


@Composable
private fun PlayListInformation(
    playlistInfo: PlaylistInfo
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
    ) {
        Image(
            painter = painterResource(id = playlistInfo.imageId),
            contentDescription = "Обложка плейлиста на SinglePlayListScreen",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(256.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = playlistInfo.name,
                style = TextStyle(
                    fontSize = 24.sp
                )
            )
        }
    }
}