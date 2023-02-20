package com.example.music_player.data

import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri
import com.example.music_player.R
import com.example.music_player.domain.FileReader

class FileReader: FileReader {

    override fun readFromRaw(context: Context): ArrayList<Song> {
        var songs = ArrayList<Song>()
        val res = context.resources
        val retriever = MediaMetadataRetriever()
        val fields = R.raw::class.java.fields

        for (field in fields){
            var id = res.getIdentifier(field.name, "raw", context.packageName)
            var uri = Uri.parse("android.resource://com.example.music_player/" + id)
            retriever.setDataSource(context, uri)
            var duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)!!.toFloat()
            songs.add(Song(field.name, id, duration))
        }

        return songs
    }

    override fun readFromExternal(context: Context): ArrayList<Song> {
        TODO("Not yet implemented")
    }


}