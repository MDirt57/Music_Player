package com.example.music_player

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.music_player.data.Song
import com.example.music_player.domain.Player


@Composable
fun SearchBar(
    text: String,
    onTyping: (String) -> Unit,
    modifier: Modifier = Modifier
){
    TextField(value = text, onValueChange = onTyping,
    leadingIcon = {
        Icon(imageVector = Icons.Default.Search, contentDescription = null)
    },
    placeholder = {Text(text = "Search")},
    singleLine = true,
    modifier = modifier.fillMaxWidth())
}

@Composable
fun SongPanel(
    modifier: Modifier = Modifier,
    name: String,
    onTap: () -> Unit
){
    Surface(
        modifier = modifier.border(1.5.dp, MaterialTheme.colorScheme.secondary, RectangleShape),
        color = MaterialTheme.colorScheme.primary
    ) {
        Row() {
            Button(
                onClick = onTap,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = name, modifier = Modifier.weight(1f), color = MaterialTheme.colorScheme.secondary)
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
                }
            }
        }
    }
}


@Composable
fun SongList(
    modifier: Modifier = Modifier,
    songs: List<Song>,
    onTap: (Song) -> Unit
){
    LazyColumn(modifier = modifier){
        items(songs){
                item -> SongPanel(name = item.name, onTap = { onTap(item) })
        }
    }
}

fun filterByName(name: String, songs: ArrayList<Song>): ArrayList<Song>{
    val filter_songs = ArrayList<Song>()
    songs.forEach {
        if (it.name.lowercase().startsWith(name.lowercase())){
            filter_songs.add(it)
        }
    }
    return filter_songs
}

@Composable
fun MainScreen(
    player: Player,
    songs: ArrayList<Song>,
    modifier: Modifier = Modifier,
){
    var isPlaying by remember { mutableStateOf(false) }
    var song by remember { mutableStateOf(Song("", Uri.parse(""), 0f)) }
    var text by remember { mutableStateOf("") }

    if (isPlaying){
        player.set(song.uri)
        player.play()
        PlayerScreen(
            player = player,
            song = song,
            prev = {song = if (songs.indexOf(song) - 1 >= 0) songs.get(songs.indexOf(song) - 1) else songs.get(songs.size - 1); player.stop()},
            next = {song = if (songs.indexOf(song) + 1 < songs.size) songs.get(songs.indexOf(song) + 1) else songs.get(0); player.stop()}
        )
        BackHandler() {
            isPlaying = false
            player.stop()
        }
    } else{
        Column(modifier = modifier) {
            SearchBar(text, {newtext -> text = newtext})
            SongList(songs = filterByName(text, songs), onTap = { item -> song = item; isPlaying = true })
        }
    }
}




