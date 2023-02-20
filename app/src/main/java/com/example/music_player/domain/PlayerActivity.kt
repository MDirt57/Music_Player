package com.example.music_player.domain

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.music_player.data.Song
import com.example.music_player.data.FileReader

class PlayerActivity(context: Context, fileReader: FileReader = FileReader()): ViewModel() {

    private val player = Player(context)
    private val _songs = fileReader.readFromRaw(context)
    val songs: ArrayList<Song>
        get() = _songs

    fun player_start(song: Int){
        player.set(song)
        player.play()
    }

    fun player_stop(){
        player.stop()
    }

}