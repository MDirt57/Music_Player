package com.example.music_player.domain

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri


class Player(context: Context){

    val player = MediaPlayer()
    val context = context
    var id = 0

    fun set(id: Int){
        this.id = id
        player.setDataSource(context, Uri.parse("android.resource://com.example.music_player/" + id))
        player.prepare()
    }

    fun isPlaying(): Boolean{
        player.setOnCompletionListener {
            println("AAAAAAAAAAAAAAAA")
        }
        return player.isPlaying
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


