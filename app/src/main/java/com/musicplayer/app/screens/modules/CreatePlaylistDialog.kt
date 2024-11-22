package com.musicplayer.app.screens.modules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.musicplayer.R
import com.musicplayer.domain.models.PlaylistInfo

@Composable
fun CreatePlaylistDialog(
    onDismissRequest: () -> Unit,
    onConfirmRequest: (newPlaylist: PlaylistInfo) -> Unit
) {
    var playlistName by remember { mutableStateOf("") }

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
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Создать плейлист"
                )
                OutlinedTextField(
                    value = playlistName,
                    onValueChange = { playlistName = it },
                    label = {Text(text = "Название")},
                    singleLine = true
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.End)
                ) {
                    TextButton(
                        onClick = onDismissRequest
                    ) {
                        Text(text = "Отмена")
                    }
                    TextButton(
                        onClick = {
                            onConfirmRequest(PlaylistInfo(
                                name = playlistName,
                                imageId = R.drawable.playlist_cover
                            ))
                        },
                        enabled = playlistName.isNotEmpty()
                    ) {
                        Text(text = "Создать")
                    }
                }
            }
        }
    }
}