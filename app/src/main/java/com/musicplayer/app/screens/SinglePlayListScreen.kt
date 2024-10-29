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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.app.screens.modules.ItemMusicTrack
import com.musicplayer.app.viewmodels.SinglePlaylistViewModel
import com.musicplayer.domain.models.PlaylistInfo
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun SinglePlayListScreen(
    playlist: PlaylistInfo
) {
    val viewModel: SinglePlaylistViewModel = koinViewModel { parametersOf(playlist) }

    val tracks by viewModel.tracks.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        PlayListInfo(playlist, viewModel)
        TrackList(tracks)
    }
}


@Composable
private fun PlayListInfo(
    playlistInfo: PlaylistInfo,
    viewModel: SinglePlaylistViewModel
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

            // Кнопка добавления трека в плейлист
            IconButton(
                onClick = {
                    viewModel.addTrack(1)
                }
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Кнопка добавления трека в плейлист",
                )
            }
        }
    }
}


@Composable
private fun TrackList(trackList: List<MusicTrackData>) {
    LazyColumn {
        itemsIndexed(trackList) { index, track ->
            ItemMusicTrack(musicTrackData = track, index = index + 1)
        }
    }
}