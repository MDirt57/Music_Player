package com.example.music_player.presentation

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.music_player.MainScreen
import com.example.music_player.data.Config
import com.example.music_player.data.FileReader
import com.example.music_player.domain.Player
import com.example.music_player.ui.theme.MyAppTheme
import com.example.music_player.data.Playlist

@Composable
fun App(applicationContext: Context){
    val filereader = FileReader()
    val config = Config(applicationContext)
    val playlists = arrayListOf(Playlist("Local", filereader.readFromExternal(applicationContext)))
    playlists.addAll(config.readPlaylists(playlists.get(0)))

    var currentTheme by remember {mutableStateOf(config.readTheme())}
    MyAppTheme(currentTheme = currentTheme) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainScreen(
                player = Player(applicationContext),
                playlists = playlists,
                change_theme = {newtheme -> currentTheme = newtheme},
                config = config
            )
        }
    }
}