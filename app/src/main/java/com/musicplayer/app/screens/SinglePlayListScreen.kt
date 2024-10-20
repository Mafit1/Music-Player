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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicplayer.R
import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.app.screens.modules.ItemMusicTrack
import com.musicplayer.app.viewmodels.SinglePlaylistViewModel

@Composable
fun SinglePlayListScreen(
    viewModel: SinglePlaylistViewModel,
    name: String = "Имя плейлиста"
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        PlayListInfo(name)
        TrackList()
    }
}


@Composable
fun PlayListInfo(name: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Playlist image in playlist screen",
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
                text = name,
                style = TextStyle(
                    fontSize = 24.sp
                )
            )

            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null,
                )
            }
        }
    }
}


@Composable
private fun TrackList() {
    LazyColumn(

    ) {
        itemsIndexed(
            listOf(
                MusicTrackData(1,"","",1,null),
                MusicTrackData(2,"","",1,null)
            )
        ) { index, item ->
            ItemMusicTrack(musicTrackData = item, index = index + 1)
        }
    }
}