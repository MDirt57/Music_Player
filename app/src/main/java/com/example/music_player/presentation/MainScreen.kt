package com.example.music_player

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.music_player.domain.PlayerActivity


@Composable
fun SearchBar(
    modifier: Modifier = Modifier
){
    TextField(value = "", onValueChange = {},
    leadingIcon = {
        Icon(imageVector = Icons.Default.Search, contentDescription = null)
    },
    placeholder = {Text(text = "Search")},
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

@Composable
fun MainScreen(
    context: Context,
    songs: ArrayList<Song>,
    modifier: Modifier = Modifier,
){
    val player = Player(context)

    var isPlaying by remember { mutableStateOf(false) }
    var song by remember { mutableStateOf(Song("",0, 0f)) }

    if (isPlaying){
        player.set(song.id)
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
            SearchBar()
            SongList(songs = songs, onTap = { item -> song = item; isPlaying = true })
        }
    }
}




