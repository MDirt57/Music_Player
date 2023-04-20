package com.example.music_player.data

import android.net.Uri
import com.example.music_player.domain.filterSongs

class Playlist(
    name_: String,
    songs_: ArrayList<Song>
){
    val name = name_
    var songs = songs_
    var songs_copy = songs_.toList()

    fun addSong(song: Song){
        if (!containSong(song)){
            songs.add(song)
        }
    }

    fun removeSong(song: Song){
        if (containSong(song)){
            songs.remove(song)
        }
    }

    fun getSong(song_name: String): Song{
        for (song0 in songs){
            if (song0.name == song_name){
                return song0
            }
        }
        return Song(song_name, Uri.parse(""), 0f)
    }

    fun containSong(song: Song): Boolean{
        for (song0 in songs){
            if (song0.name.equals(song.name)){
                return true
            }
        }
        return false
    }

    fun filter(name: String){
        songs = filterSongs(name, songs_copy)
    }

    fun cancelChanges(){
        songs = ArrayList<Song>(songs_copy)
    }

    fun applyChanges(){
        songs_copy = songs.toList()
    }
}