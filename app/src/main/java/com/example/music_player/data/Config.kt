package com.example.music_player.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.net.Uri
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class Config(context: Context){
    val sharedPref = context.getSharedPreferences("Config", MODE_PRIVATE)
    val writer = sharedPref.edit()
    val gson = Gson()
    val json = sharedPref.getString("playlists", null)

    fun readPlaylists(local: Playlist): ArrayList<Playlist>{
        val type = object: TypeToken<ArrayList<ArrayList<String>>>() {}.type
        val playlists_ = if (json != null) gson.fromJson(json, type) else ArrayList<ArrayList<String>>()
        val playlists_internal = ArrayList<Playlist>()
        for (playlist in playlists_){
            val songs = ArrayList<Song>()
            for (song in playlist.subList(1, playlist.lastIndex + 1)){
                songs.add(local.getSong(song))
            }
            playlists_internal.add(Playlist(playlist.get(0), songs))
        }
        return playlists_internal
    }

    fun writePlaylists(playlists: ArrayList<Playlist>){
        val playlists_new = ArrayList<ArrayList<String>>()
        for (playlist in playlists){
            val playlist_ = ArrayList<String>()
            playlist_.add(playlist.name)
            for (song in playlist.songs_copy){
                playlist_.add(song.name)
            }
            playlists_new.add(playlist_)
        }
        val json = gson.toJson(playlists_new)
        writer.putString("playlists", json)
        writer.apply()
    }

    fun readTheme(): String{
        return sharedPref.getString("theme", "Classic")!!
    }

    fun writeTheme(theme: String){
        writer.putString("theme", theme)
        writer.apply()
    }

    fun readIndex(): Int{
        return sharedPref.getInt("index", 0)
    }

    fun writeIndex(index: Int){
        writer.putInt("index", index)
        writer.apply()
    }
}