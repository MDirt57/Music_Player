package com.example.music_player.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.music_player.data.Playlist
import com.example.music_player.data.Song


@Composable
fun AddSongsPanel(
    playlist: Playlist,
    local_songs: ArrayList<Song>,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
){
    val screenHeight = LocalConfiguration.current.screenHeightDp

    Popup(
        properties = PopupProperties(focusable = true),
        alignment = Alignment.BottomEnd,
        onDismissRequest = {}
    ){
        Column(modifier = modifier.border(border = BorderStroke(2.dp, Color.White), RectangleShape)){
            SongList(songs = local_songs, onTap = {song -> if (!playlist.containSong(song)) playlist.addSong(song) else playlist.removeSong(song)}, icon = {song -> playlist.containSong(song)}, modifier = Modifier.height((screenHeight - 148).dp))
            BottomPanel(onPress = {onCancel(); playlist.songs.sortBy { it.name }; playlist.applyChanges()}, onCancel = {onCancel(); playlist.cancelChanges()}, color = MaterialTheme.colorScheme.primary, actionName = "Add", modifier = Modifier.fillMaxWidth())
        }
    }
}