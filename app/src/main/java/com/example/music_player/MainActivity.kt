package com.example.music_player

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.music_player.data.Config
import com.example.music_player.presentation.App
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermission(this)
        setContent {
            App(applicationContext = applicationContext)
        }
    }

    override fun onStop() {
        super.onStop()
        val config = Config(applicationContext)
        config.writeIndex(1, 0)
    }

    fun requestPermission(activity: MainActivity){
        runBlocking {
            launch{
                while (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(activity, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 101)
                    delay(1000)
                }
            }
        }
    }

}
