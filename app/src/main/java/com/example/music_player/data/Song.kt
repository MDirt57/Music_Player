package com.example.music_player.data

import android.net.Uri

data class Song(
    val name: String,
    val uri: Uri,
    val duration: Float
)