package com.example.music_player

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.music_player.data.FileReader
import com.example.music_player.domain.Player
import com.example.music_player.domain.test
import com.example.music_player.ui.theme.Music_PlayerTheme
import java.util.jar.Manifest

class MainActivity : ComponentActivity() {
    val filereader = FileReader()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val READ_EXTERNAL_STORAGE_PERMISSION_CODE = 101
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), READ_EXTERNAL_STORAGE_PERMISSION_CODE)
        }

        setContent {
            Music_PlayerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(player = Player(applicationContext), songs = filereader.readFromExternal(applicationContext))
                    test()
                }
            }
        }
    }
}
