package com.example.music_player.presentation

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.music_player.SongList
import com.example.music_player.data.Config
import com.example.music_player.data.Playlist
import com.example.music_player.data.Song


@Composable
fun PlaylistUI(
    playlists: List<Playlist>,
    onTap: (Song) -> Unit,
    change_theme: (String) -> Unit,
    config: Config,
    modifier: Modifier = Modifier
){
    val state = rememberLazyListState()

    var name = if (state.firstVisibleItemIndex == 0) "Local" else "Network"
    var is_change by remember {mutableStateOf(false)}
    var is_createplaylist by remember {mutableStateOf(false)}
    var playlistName by remember {mutableStateOf("")}
    println(playlistName)

    val screenWidth = with(LocalDensity.current) {
        LocalConfiguration.current.screenWidthDp * density
    }

    LaunchedEffect(key1 = Unit){
        state.scrollToItem(config.readIndex())
    }

    LaunchedEffect(key1 = name){
        config.writeIndex(state.firstVisibleItemIndex)
    }

    LaunchedEffect(key1 = state.isScrollInProgress){
        if (!state.isScrollInProgress && state.firstVisibleItemScrollOffset >= screenWidth / 2){
            state.animateScrollToItem(state.firstVisibleItemIndex + 1)
        }
        else{
            state.animateScrollToItem(state.firstVisibleItemIndex)
        }
    }

    if (is_change){
        ChangeThemePanel(on_close = {is_change = false}, change_theme, config)
    }
    if (is_createplaylist){
        CreatePlaylistPanel(
            onPress = {newtext -> playlistName = newtext},
            onCancel = {is_createplaylist = false},
            color = MaterialTheme.colorScheme.primary
        )
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        SettingsPanel(name = name, palette = {is_change = true}, createPlaylist = {is_createplaylist = true})
        LazyRow(
            state = state,
            contentPadding = PaddingValues(1.dp),
            horizontalArrangement = Arrangement.spacedBy(1.dp),
            modifier = Modifier.fillMaxWidth()
        ){
            items(playlists){
                    item -> SongList(songs = item.songs, onTap = onTap,
                modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp))
            }
        }
    }

}