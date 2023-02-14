package com.example.music_player

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri


class PlayerActivity(context: Context){

    val player = MediaPlayer()
    val context = context
    fun set(id: Int){
        player.setDataSource(context, Uri.parse("android.resource://com.example.music_player/" + id))
        player.prepare()
    }

    fun isPlaying(): Boolean{
        return player.isPlaying
    }

    fun seekTo(time: Float){
        player.seekTo(time.toInt())
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


