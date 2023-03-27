package com.example.music_player

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.music_player.data.Playlist
import com.example.music_player.data.Song
import com.example.music_player.domain.Player
import com.example.music_player.presentation.PlaylistUI


@Composable
fun SearchBar(
    tempText: String,
    onTyping: (String) -> Unit,
    onPress: (String) -> Unit,
    focusManager: FocusManager,
    modifier: Modifier = Modifier
){
    TextField(value = tempText, onValueChange = onTyping,
    leadingIcon = {
        Icon(imageVector = Icons.Default.Search, contentDescription = null)
    },
    placeholder = {Text(text = "Search")},
    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
    keyboardActions = KeyboardActions(onDone = {
        onPress(tempText)
        focusManager.clearFocus()
    }),
    singleLine = true,
    modifier = modifier.fillMaxWidth())
}

@Composable
fun StatefullSearchBar(
    text: String,
    onPress: (String) -> Unit,
    focusManager: FocusManager,
    modifier: Modifier = Modifier
){
    var tempText by remember {mutableStateOf(text)}
    SearchBar(
        tempText = tempText,
        onTyping = {newtext -> tempText = newtext},
        onPress = onPress,
        focusManager = focusManager,
        modifier = modifier
    )
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
    songs: List<Song>,
    onTap: (Song) -> Unit,
    modifier: Modifier = Modifier
){
    LazyColumn(modifier = modifier){
        items(songs){
                item -> SongPanel(name = item.name, onTap = { onTap(item) })
        }
    }
}


@Composable
fun MainScreen(
    player: Player,
    songs: ArrayList<Song>,
    change_theme: (String) -> Unit,
    modifier: Modifier = Modifier,
){
    var isPlaying by remember { mutableStateOf(false) }
    var song by remember { mutableStateOf(Song("", Uri.parse(""), 0f)) }
    var text by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    val playlist: ArrayList<Playlist> = ArrayList<Playlist>()
    playlist.add(Playlist("number1", songs))
    playlist.add(Playlist("number2", songs))
    playlist.add(Playlist("number3", songs))

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
            StatefullSearchBar(text, {newtext -> text = newtext}, focusManager)
            PlaylistUI(playlists = playlist, onTap = {item -> song = item; isPlaying = true}, change_theme)
//            SongList(songs = filterSongs(text, songs), onTap = { item -> song = item; isPlaying = true })
        }
    }
}




