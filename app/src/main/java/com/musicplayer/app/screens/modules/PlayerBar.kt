package com.musicplayer.app.screens.modules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.media3.common.Player
import com.example.musicplayer.R
import com.musicplayer.app.viewmodels.SharedPlayerViewModel
import com.musicplayer.domain.models.MusicTrackData

@Composable
fun PlayerBar(
    sharedPlayerViewModel: SharedPlayerViewModel
) {
    val isPlaying by sharedPlayerViewModel.isPlaying.collectAsState(initial = false)
    val isShuffleEnabled by sharedPlayerViewModel.isShuffleEnabled.collectAsState(initial = false)
    val repeatMode by sharedPlayerViewModel.repeatMode.collectAsState(initial = Player.REPEAT_MODE_OFF)
    val currentTrackIndex by sharedPlayerViewModel.currentTrackIndex.collectAsState(initial = -1)
    val currentPlaylist by sharedPlayerViewModel.currentPlaylist.collectAsState(initial = emptyList())
    val currentPlayerState by sharedPlayerViewModel.currentPlayerState.collectAsState(initial = Player.STATE_IDLE)
    val currentTrack = if (currentPlayerState == Player.STATE_READY) currentPlaylist[currentTrackIndex] else MusicTrackData(name = "", author = "", duration = 0)

    Surface(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 8.dp,
        tonalElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(2.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = currentTrack.name,
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = currentTrack.author,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = {sharedPlayerViewModel.toggleShuffle()}) {
                    Icon(
                        painter = if (isShuffleEnabled) painterResource(id = R.drawable.baseline_shuffle_on_24) else painterResource(id = R.drawable.baseline_shuffle_24),
                        contentDescription = "Шафл"
                    )
                }
                IconButton(onClick = {sharedPlayerViewModel.previousTrack()}) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_skip_previous_24),
                        contentDescription = "Предыдущий трек"
                    )
                }
                IconButton(onClick = { if (isPlaying) sharedPlayerViewModel.pause() else sharedPlayerViewModel.play() }) {
                    Icon(
                        painter = if (isPlaying) painterResource(id = R.drawable.baseline_pause_24) else painterResource(id = R.drawable.baseline_play_arrow_24),
                        contentDescription = if (isPlaying) "Пауза" else "Играть"
                    )
                }
                IconButton(onClick = {sharedPlayerViewModel.nextTrack()}) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_skip_next_24),
                        contentDescription = "Следующий трек"
                    )
                }
                IconButton(onClick = {sharedPlayerViewModel.toggleRepeatMode()}) {
                    Icon(
                        painter = when (repeatMode) {
                            Player.REPEAT_MODE_ALL -> painterResource(id = R.drawable.baseline_repeat_on_24)
                            Player.REPEAT_MODE_ONE -> painterResource(id = R.drawable.baseline_repeat_one_on_24)
                            else -> painterResource(id = R.drawable.baseline_repeat_24)
                        },
                        contentDescription = "Зацикливание"
                    )
                }
            }
        }
    }
}