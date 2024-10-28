package com.musicplayer.app.screens.modules

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String
) {
    androidx.compose.material3.TopAppBar(
        title = { Text("title")}
    )
}