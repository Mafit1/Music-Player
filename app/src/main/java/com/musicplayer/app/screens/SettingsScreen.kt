package com.musicplayer.app.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.musicplayer.app.viewmodels.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    paddingValues: PaddingValues
) {
    var addTestPlaylistsDialog by remember { mutableStateOf(false) }

    if (addTestPlaylistsDialog) {
        AddTestPlaylistsDialog(
            onDismissRequest = {
                addTestPlaylistsDialog = false
            },
            onConfirmRequest = {
                viewModel.createTestPlaylists()
                addTestPlaylistsDialog = false
            }
        )
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(bottom = 150.dp)
    ) {
        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { addTestPlaylistsDialog = true }
                .height(50.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Text(text = "Добавить тестовые плейлисты")
            }
        }
    }
}

@Composable
private fun AddTestPlaylistsDialog(
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = "Вы уверены, что хотите добавить 5 новых тестовых плейлистов?")
        },
        text = {
            Text(text = "Названия плейлистов будут пронумерованы.")
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