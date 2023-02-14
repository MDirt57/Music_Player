package com.example.music_player

import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri
import java.time.Duration

data class Song(
    val name: String,
    val id: Int,
    val duration: Float
)

fun readSongs(): ArrayList<Song>{
    var songs = ArrayList<Song>()
    ///
//    songs.add(Song("disfigure", R.raw.disfigure))
//    songs.add(Song("omen", R.raw.omen))
//    songs.add(Song("seasons", R.raw.seasons))
    ///
    return songs
}

fun read(context: Context):  ArrayList<Song>{
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