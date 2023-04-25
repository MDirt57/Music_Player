package com.example.music_player.domain

import com.example.music_player.data.Playlist
import com.example.music_player.data.Song
import kotlinx.coroutines.delay

fun time_format(milliseconds: Float): String{
    val sec = milliseconds/1000
    val minutes = (sec/60).toInt()
    val seconds = (sec - 60*minutes).toInt()
    return "${if (minutes>=10) minutes else "0$minutes"}:${if (seconds>=10) seconds else "0$seconds"}"
}

fun filterSongs(name: String, songs: List<Song>): ArrayList<Song>{
    val filter_songs = ArrayList<Song>(songs.filter { song -> song.name.lowercase().startsWith(name.lowercase()) })
    return filter_songs
}

fun moveSong(changeSong: (Song) -> Unit, direct: Int, player: Player, song: Song, current_playlist: Playlist){
    var new_song = song

    if (direct == 1){
        new_song = if (current_playlist.songs.indexOf(song) + 1 < current_playlist.songs.size) current_playlist.songs.get(current_playlist.songs.indexOf(song) + 1) else current_playlist.songs.get(0)
    }else if (direct == -1){
        new_song = if (current_playlist.songs.indexOf(song)+direct >= 0) current_playlist.songs.get(current_playlist.songs.indexOf(song)+direct) else current_playlist.songs.get(current_playlist.songs.size+direct)
    }
    player.stop()
    changeSong(new_song)
    player.set(new_song.uri)
    player.play()
}

suspend fun nextSong(
    player: Player,
    duration: Float,
    updatetime: () -> Unit,
    next: () -> Unit
){
    while(true){
        if (player.position() == 0f){
            updatetime()
        }else if (player.position() >= duration - 900){
            next()
        }
        delay(1000L)
    }
}

suspend fun slideChange(
    perSecond: () -> Unit
){
    while(true){
        delay(1000L)
        perSecond()
    }
}
