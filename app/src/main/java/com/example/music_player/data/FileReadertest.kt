package com.example.music_player.data

import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri
import com.example.music_player.R
import java.io.File


fun sample(): ArrayList<Song>{
    var songs = ArrayList<Song>()
    ///
    songs.add(Song("disfigure", R.raw.disfigure, 0f))
    songs.add(Song("omen", R.raw.omen, 0f))
    songs.add(Song("seasons", R.raw.seasons, 0f))
    ///
    return songs
}

fun readTest(context: Context): ArrayList<Song>{
    var songs = ArrayList<Song>()
    val res = context.resources
    val retriever = MediaMetadataRetriever()
    val fields = R.raw::class.java.fields
    for (field in fields){
        var id = res.getIdentifier(field.name, "raw", context.packageName)
        var uri = Uri.parse("android.resource://com.example.music_player/" + id)
        retriever.setDataSource(context, uri)
        songs.add(Song(field.name, id, retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)!!.toFloat()))
    }
    return songs
}


fun readExternal(){

    val directory = File("Phone storage/Download/")
    val files = directory.listFiles()
    println(directory)
    println(files)
    files?.forEach{
        println(it)
    }
}

fun read(context: Context):  ArrayList<Song>{
    var songs = ArrayList<Song>()
    val res = context.resources
    val retriever = MediaMetadataRetriever()
    val fields = R.raw::class.java.fields
    for (field in fields){
        println(field.name)
        var id = res.getIdentifier(field.name, "raw", context.packageName)
        var uri = Uri.parse("android.resource://com.example.music_player/" + id)
        retriever.setDataSource(context, uri)
        songs.add(Song(field.name, id, retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)!!.toFloat()))
    }
    return songs
}