package com.musicplayer.app.screens.modules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.musicplayer.app.viewmodels.SinglePlaylistViewModel
import com.musicplayer.domain.models.MusicTrackData

@Composable
fun TrackSettingsDialogPlaylist(
    track: MusicTrackData,
    singlePlaylistViewModel: SinglePlaylistViewModel,
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                SettingsItem(
                    text = "Убрать из плейлиста",
                    image = Icons.Default.Close,
                    onClick = {
                        singlePlaylistViewModel.removeTrackFromPlaylist(track)
                        onDismissRequest()
                    }
                )
            }
        }
    }
}