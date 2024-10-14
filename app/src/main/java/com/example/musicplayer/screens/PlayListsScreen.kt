package com.example.musicplayer.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.domain.models.PlaylistData
import com.example.musicplayer.screens.modules.PlayList
import com.example.musicplayer.viewmodels.PlaylistsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayListsScreen(
    viewModel: PlaylistsViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = Color.Gray,
                    navigationIconContentColor = Color.Black,
                    scrolledContainerColor = Color.Gray,
                    titleContentColor = Color.Black,
                    actionIconContentColor = Color.Black
                ),
                title = {
                    Text("Мои плейлисты")
                },
                actions = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        PlayListsList(paddingValues = innerPadding)
    }
}


@Composable
private fun PlayListsList(paddingValues: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        itemsIndexed(
            listOf(
                PlaylistData(1, "abc", ""),
                PlaylistData(2, "cba", "")
            )
        ) { _, item ->
            PlayList(playListData = item)
        }
    }
}