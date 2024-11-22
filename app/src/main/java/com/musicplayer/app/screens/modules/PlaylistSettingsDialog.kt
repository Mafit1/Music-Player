package com.musicplayer.app.screens.modules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.musicplayer.app.viewmodels.PlaylistsViewModel
import com.musicplayer.domain.models.PlaylistInfo

@Composable
fun PlaylistSettingsDialog(
    playlist: PlaylistInfo,
    playlistsViewModel: PlaylistsViewModel,
    onDismissRequest: () -> Unit
) {
    var deleteConfirmationDialog by remember { mutableStateOf(false) }

    if (deleteConfirmationDialog) {
        DeleteConfirmationDialog(
            onDismissRequest = {
                deleteConfirmationDialog = false
                onDismissRequest()
            },
            onConfirmRequest = {
                playlistsViewModel.deletePlaylist(playlist)
                onDismissRequest()
            }
        )
    }

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
                    text = "Удалить плейлист",
                    image = Icons.Default.Delete,
                    onClick = {
                        deleteConfirmationDialog = true
                    }
                )
            }
        }
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = "Вы уверены, что хотите удалить плейлист?")
        },
        text = {
            Text(text = "Данное действие приведет к удалению данного плейлиста.")
        },
        confirmButton = {
            TextButton(
                onClick = onConfirmRequest
            ) {
                Text(text = "Да")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest
            ) {
                Text(text = "Нет")
            }
        },
        modifier = Modifier
            .fillMaxWidth()
    )
}