package com.example.music_player

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.music_player.data.Config
import com.example.music_player.data.Playlist
import com.example.music_player.data.Song
import com.example.music_player.domain.Player
import com.example.music_player.domain.moveSong
import com.example.music_player.presentation.*

@Composable
fun MainScreen(
    player: Player,
    playlists: ArrayList<Playlist>,
    change_theme: (String) -> Unit,
    config: Config,
    modifier: Modifier = Modifier,
){
    var isPlaying by remember { mutableStateOf(false) }
    var song by remember { mutableStateOf(Song("", Uri.parse(""), 0f)) }
    var text by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    var is_changetheme by remember {mutableStateOf(false)}
    var is_createplaylist by remember {mutableStateOf(false)}
    var is_addingsongs by remember {mutableStateOf(false)}

    val playlists = remember {mutableStateListOf<Playlist>(*playlists.map {it}.toTypedArray())}
    var current_playlist by remember { mutableStateOf(playlists.get(0)) }
    current_playlist.filter(text)

    LaunchedEffect(key1 = current_playlist, key2 = is_createplaylist, key3 = is_addingsongs){
        config.writePlaylists(if (playlists.size > 1) ArrayList<Playlist>(playlists.subList(1, playlists.lastIndex + 1)) else ArrayList<Playlist>())
    }

    if (is_changetheme){
        ChangeThemePanel(on_close = {is_changetheme = false}, change_theme, config)
    }
    if (is_createplaylist){
        CreatePlaylistPanel(
            onPress = {newtext -> playlists.add(Playlist(newtext, ArrayList<Song>())) },
            onCancel = {is_createplaylist = false},
            color = MaterialTheme.colorScheme.primary
        )
    }

    if (is_addingsongs){
        AddSongsPanel(
            config = config,
            playlist = current_playlist,
            local_songs = playlists.get(0).songs,
            onCancel = { is_addingsongs = false }
        )
    }

    if (isPlaying){
        PlayerScreen(
            player = player,
            song = song,
            prev = {moveSong({new_song -> song = new_song}, -1, player, song, current_playlist)},
            next = {moveSong({new_song -> song = new_song}, 1, player, song, current_playlist)}
        )
        BackHandler() {
            isPlaying = false
            player.stop()
        }
    } else{
        Column(modifier = modifier) {
            StatefullSearchBar(text, {newtext -> text = newtext}, focusManager, Modifier.height(50.dp))
            SettingsPanel(
                name = current_playlist.name,
                palette = {is_changetheme = true},
                addSongs = {is_addingsongs = true},
                createPlaylist = {is_createplaylist = true},
                deletePlaylist = {val index = playlists.indexOf(current_playlist) - 1; playlists.remove(current_playlist); current_playlist = playlists.get(index)},
                modifier = Modifier.height(50.dp))
            AllPlaylists(playlists = playlists, onTap = {item -> song = item; isPlaying = true; player.set(song.uri); player.play()}, onScroll = {current -> current_playlist = current}, config)
        }
    }
}




