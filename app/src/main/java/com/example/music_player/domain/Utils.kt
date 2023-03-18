package com.example.music_player.domain

import com.example.music_player.data.Song
import kotlinx.coroutines.delay

fun time_format(milliseconds: Float): String{
    val sec = milliseconds/1000
    val minutes = (sec/60).toInt()
    val seconds = (sec - 60*minutes).toInt()
    return "${if (minutes>=10) minutes else "0$minutes"}:${if (seconds>=10) seconds else "0$seconds"}"
}

fun filterTest(name: String, songs: ArrayList<Song>): ArrayList<Song>{
    val filter_songs = ArrayList<Song>(songs.filter { song -> song.name.lowercase().startsWith(name.lowercase()) })
    return filter_songs
}


suspend fun slideChange(
    perSecond: () -> Unit
){
    while(true){
        delay(1000L)
        perSecond()
    }
}