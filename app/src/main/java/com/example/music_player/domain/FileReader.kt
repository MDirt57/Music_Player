package com.example.music_player.domain

import android.content.Context
import com.example.music_player.data.Song

interface FileReader {
    fun readFromExternal(context: Context): ArrayList<Song>
}