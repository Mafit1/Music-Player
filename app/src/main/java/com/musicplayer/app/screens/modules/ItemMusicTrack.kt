package com.musicplayer.app.screens.modules

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.musicplayer.domain.models.MusicTrackData

@Preview(showBackground = true)
@Composable
fun ItemMusicTrack(
    musicTrackData: MusicTrackData = MusicTrackData(1, "Name", "Author", 210000, null),
    index: Int = 1
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 6.dp, top = 12.dp, bottom = 12.dp, end = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(

            ) {
                Text(
                    text = index.toString(),
                    textAlign = TextAlign.End
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = musicTrackData.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = musicTrackData.author,
                    style = TextStyle(fontSize = 12.sp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Row {
            Text(
                text = formatDuration(musicTrackData.duration)
            )
        }
    }

}

@SuppressLint("DefaultLocale")
fun formatDuration(milliSeconds: Long) : String {
    val minutes = (milliSeconds / 1000) / 60
    val seconds = (milliSeconds / 1000) % 60
    return String.format("%1d:%02d", minutes, seconds)
}