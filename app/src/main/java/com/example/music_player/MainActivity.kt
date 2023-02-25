package com.example.music_player

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.music_player.data.FileReader
import com.example.music_player.domain.Player
import com.example.music_player.ui.theme.Music_PlayerTheme

class MainActivity : ComponentActivity() {
    val filereader = FileReader()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Music_PlayerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(player = Player(applicationContext), songs = filereader.readFromExternal(applicationContext))
                }
            }
        }
    }
}