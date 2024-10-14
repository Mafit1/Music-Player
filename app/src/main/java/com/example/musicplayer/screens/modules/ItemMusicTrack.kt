package com.example.musicplayer.screens.modules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.models.MusicTrackData

@Preview(showBackground = true)
@Composable
fun ItemMusicTrack(musicTrackData: MusicTrackData = MusicTrackData(1, "", "", 1, null), index : Int = 1) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
    ) {
        Text(
            text = index.toString(),
            modifier = Modifier
                .padding(24.dp)
        )
        Column(

        ) {
            Text(
                text = musicTrackData.name
            )
            Text(
                text = musicTrackData.author,
                style = TextStyle(fontSize = 12.sp)
            )
        }
    }
}