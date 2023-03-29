package com.example.music_player.presentation

import android.content.Context
import android.provider.Settings.Global.getString
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.music_player.MainScreen
import com.example.music_player.data.Config
import com.example.music_player.data.FileReader
import com.example.music_player.domain.Player
import com.example.music_player.ui.theme.MyAppTheme

@Composable
fun App(applicationContext: Context){
    val filereader = FileReader()
    val config = Config(activity = applicationContext, resources = resou)

    var currentTheme by remember {mutableStateOf("")}
    MyAppTheme(currentTheme = currentTheme) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainScreen(
                player = Player(applicationContext),
                songs = filereader.readFromExternal(applicationContext),
                change_theme = {newtheme -> currentTheme = newtheme}
            )
        }
    }
}