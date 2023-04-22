package com.example.music_player.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.music_player.data.Config
import com.example.music_player.data.Playlist
import com.example.music_player.data.Song
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun AllPlaylists(
    playlists: List<Playlist>,
    onTap: (Song) -> Unit,
    onScroll: (Playlist) -> Unit,
    config: Config,
    modifier: Modifier = Modifier
){
    val state = rememberLazyListState()

    LaunchedEffect(key1 = Unit){
        state.scrollToItem(config.readIndex(0))
    }

    LaunchedEffect(key1 = state.firstVisibleItemIndex){
        config.writeIndex(0, state.firstVisibleItemIndex)
        onScroll(playlists.get(state.firstVisibleItemIndex))
    }

    LazyRow(
        state = state,
        contentPadding = PaddingValues(1.dp),
        horizontalArrangement = Arrangement.spacedBy(1.dp),
        flingBehavior = rememberSnapperFlingBehavior(lazyListState = state),
        modifier = modifier.fillMaxWidth()
    ){
        items(playlists){
                item -> SongList(config, songs = item.songs, onTap = onTap,
            modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp))
        }
    }
}