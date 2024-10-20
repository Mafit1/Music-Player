package com.musicplayer.app.screens.modules

import androidx.compose.foundation.layout.Arrangement
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
import com.musicplayer.domain.models.MusicTrackData

@Preview(showBackground = true)
@Composable
fun ItemMusicTrack(
    musicTrackData: MusicTrackData = MusicTrackData(1, "Name", "Author", 60, null),
    index: Int = 1
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = index.toString(),
                modifier = Modifier
                    .padding(start = 8.dp, end = 25.dp)
            )
            Column {
                Text(
                    text = musicTrackData.name
                )
                Text(
                    text = musicTrackData.author,
                    style = TextStyle(fontSize = 12.sp)
                )
            }
        }
        Row {
            Text(
                text = formatSeconds(musicTrackData.durationSec)
            )
        }
    }
}

fun formatSeconds(seconds: Long) : String {
    return "${(seconds / 60)}:${(seconds % 60).toString().padStart(2, '0')}"
}