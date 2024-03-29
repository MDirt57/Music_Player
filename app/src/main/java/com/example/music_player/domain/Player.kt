package com.example.music_player.domain

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri

class Player(context: Context){

    val player = MediaPlayer()
    val context = context
    var uri: Uri = Uri.parse("")

    fun set(uri: Uri){
        this.uri = uri
        val contentResolver = context.contentResolver
        val fileDescriptor = contentResolver.openFileDescriptor(uri, "r")?.fileDescriptor
        player.reset()
        player.setDataSource(fileDescriptor)
        player.prepare()
    }

    fun seekTo(time: Float){
        player.seekTo(time.toInt())
    }

    fun position(): Float{
        return player.currentPosition.toFloat()
    }

    fun play(){
        player.start()
    }

    fun pause(){
        player.pause()
    }

    fun stop(){
        player.stop()
    }

}


