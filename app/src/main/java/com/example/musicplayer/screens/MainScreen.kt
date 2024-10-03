package com.example.musicplayer.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.musicplayer.navigation.BottomBar
import com.example.musicplayer.navigation.BottomNavGraph

@Preview(showBackground = true)
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { innerPadding ->
        innerPadding
        BottomNavGraph(navController = navController)
    }
}