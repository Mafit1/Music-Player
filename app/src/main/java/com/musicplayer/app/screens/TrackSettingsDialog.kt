package com.musicplayer.app.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.musicplayer.app.viewmodels.FullTrackListViewModel
import com.musicplayer.domain.models.MusicTrackData
import com.musicplayer.domain.models.PlaylistInfo

@Composable
fun TrackSettingsDialog(
    track: MusicTrackData,
    onDismissRequest: () -> Unit,
    fullTrackListViewModel: FullTrackListViewModel
) {
    var playlistSelectDialog by remember { mutableStateOf(false) }
    var deleteConfirmation by remember { mutableStateOf(false) }
    var editTrackDialog by remember { mutableStateOf(false) }

    if (editTrackDialog) {
        EditTrackDialog(
            onDismissRequest = {
                editTrackDialog = false
            },
            onSaveRequest = { newName, newAuthor ->
                fullTrackListViewModel.updateTrackFields(track, newName, newAuthor)
                onDismissRequest()
            },
            track = track
        )
    }

    if (playlistSelectDialog) {
        PlaylistSelectDialog(
            onDismissRequest = {
                playlistSelectDialog = false
            },
            onPlaylistSelected = { playlist ->
                fullTrackListViewModel.addTrackToPlaylist(track, playlist)
                onDismissRequest()
            },
            playlists = fullTrackListViewModel.getAllPlaylists().collectAsState(initial = emptyList()).value
        )
    }

    if (deleteConfirmation) {
        DeleteConfirmationDialog(
            onDismissRequest = {
                deleteConfirmation = false
                onDismissRequest()
            },
            onConfirmRequest = {
                fullTrackListViewModel.deleteTrack(track)
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
                    text = "Добавить в плейлист",
                    image = Icons.Default.Add,
                    onClick = {
                        playlistSelectDialog = true
                    }
                )
                SettingsItem(
                    text = "Изменить",
                    image = Icons.Default.Create,
                    onClick = {
                        editTrackDialog = true
                    }
                )
                SettingsItem(
                    text = "Удалить",
                    image = Icons.Default.Delete,
                    onClick = {
                        deleteConfirmation = true
                    }
                )
            }
        }
    }
}

@Composable
private fun PlaylistSelectDialog(
    onDismissRequest: () -> Unit,
    onPlaylistSelected: (playlist: PlaylistInfo) -> Unit,
    playlists: List<PlaylistInfo>
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(250.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Добавить в плейлист",
                    modifier = Modifier
                        .padding(4.dp)
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                ) {
                    items(playlists) { playlist ->
                        PlaylistItem(
                            playlist = playlist,
                            onClick = { onPlaylistSelected(playlist) }
                        )
                    }
                }
                TextButton(
                    onClick = onDismissRequest,
                    modifier = Modifier
                        .align(Alignment.End)
                ) {
                    Text(text = "Отмена")
                }
            }
        }
    }
}

@Composable
private fun EditTrackDialog(
    onDismissRequest: () -> Unit,
    onSaveRequest: (newName: String, newAuthor: String) -> Unit,
    track: MusicTrackData
) {
    var trackName by remember { mutableStateOf(track.name) }
    var trackAuthor by remember { mutableStateOf(track.author) }

    val oldName = track.name
    val oldAuthor = track.author

    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(200.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .padding(12.dp),
                ) {
                    OutlinedTextField(
                        value = trackName,
                        onValueChange = { trackName = it },
                        label = { Text("Название") },
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = trackAuthor,
                        onValueChange = { trackAuthor = it },
                        label = { Text("Автор") },
                        singleLine = true
                    )
                }
                Row(
                    modifier = Modifier
                        .align(Alignment.End)
                ) {
                    TextButton(
                        onClick = onDismissRequest
                    ) { Text(text = "Отмена") }
                    TextButton(
                        onClick = {
                            onSaveRequest(trackName, trackAuthor)
                        },
                        enabled = trackName != oldName || trackAuthor != oldAuthor
                    ) { Text(text = "Сохранить") }
                }
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
            Text(text = "Вы уверены, что хотите удалить трек?")
        },
        text = {
            Text(text = "Данное действие приведет к удалению этого трека из всех плейлистов.")
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

@Composable
private fun PlaylistItem(
    playlist: PlaylistInfo,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Text(
            text = playlist.name
        )
    }
}

@Composable
private fun SettingsItem(
    text: String,
    image: ImageVector,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Icon(
                imageVector = image,
                contentDescription = "Иконка элемента опций трека"
            )
            Text(
                text = text,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            )
        }
    }
}