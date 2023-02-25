package com.example.music_player.data

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.provider.MediaStore
import com.example.music_player.R
import com.example.music_player.domain.FileReader

class FileReader: FileReader {

    override fun readFromExternal(context: Context): ArrayList<Song> {
        var songs = ArrayList<Song>()
        val contentResolver = context.contentResolver

        val uri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DURATION,
        )
        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"
        val sortOrder = "${MediaStore.Audio.Media.TITLE} ASC"
        val cursor: Cursor? = contentResolver.query(
            uri,
            projection,
            selection,
            null,
            sortOrder
        )
        if (cursor != null && cursor.moveToFirst()) {
            val titleIndex = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
            val idIndex = cursor.getColumnIndex(MediaStore.Audio.Media._ID)
            val durationIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)

            do {
                val title = if (titleIndex >= 0) cursor.getString(titleIndex) else ""
                val id = if (idIndex >= 0) cursor.getLong(idIndex) else 0
                val uri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)
                val duration = if (durationIndex >= 0) cursor.getFloat(durationIndex) else 0f

                songs.add(Song(title, uri, duration))
            } while (cursor.moveToNext())
        }
        cursor?.close()
        return songs
    }
}