package com.musicplayer.app.screens.modules

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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

@Composable
fun ItemMusicTrack(
    musicTrackData: MusicTrackData = MusicTrackData(1, "dghjdghjdghjfghjfghjfgjfgjfgjfgjfgjfghjjghkhgkghjkhgkghkghkhgkghkghkhgkykgh", "Author", 210000, null),
    index: Int = 1,
    onClick: () -> Unit,
    isPlaying: Boolean = false
) {
    val backgroundColor = if (isPlaying) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f) else MaterialTheme.colorScheme.background
    val textColor = if (isPlaying) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(backgroundColor)
            .padding(12.dp)
    ) {
        Text(
            text = index.toString(),
            textAlign = TextAlign.End,
            color = textColor
        )
        Column(
            modifier = Modifier
                .padding(start = 12.dp)
                .weight(1f)
        ) {
            Text(
                text = musicTrackData.name,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = textColor
            )
            Text(
                text = musicTrackData.author,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = textColor
            )
        }
        Text(
            text = formatDuration(musicTrackData.duration),
            color = textColor,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@SuppressLint("DefaultLocale")
fun formatDuration(milliSeconds: Long) : String {
    val minutes = (milliSeconds / 1000) / 60
    val seconds = (milliSeconds / 1000) % 60
    return String.format("%1d:%02d", minutes, seconds)
}