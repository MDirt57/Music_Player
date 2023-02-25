package com.example.music_player.data

import android.net.Uri
import java.io.FileDescriptor

data class Song(
    val name: String,
    val uri: Uri,
    val duration: Float
)