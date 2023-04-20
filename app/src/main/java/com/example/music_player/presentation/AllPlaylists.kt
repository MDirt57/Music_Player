package com.example.music_player.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.paging.ExperimentalPagingApi
import com.example.music_player.data.Config
import com.example.music_player.data.Playlist
import com.example.music_player.data.Song

@OptIn(ExperimentalPagingApi::class)
@Composable
fun AllPlaylists(
    playlists: List<Playlist>,
    onTap: (Song) -> Unit,
    onScroll: (Playlist) -> Unit,
    config: Config,
    modifier: Modifier = Modifier
){
    val state = rememberLazyListState()
    val screenWidth = with(LocalDensity.current) {
        LocalConfiguration.current.screenWidthDp * density
    }

    LaunchedEffect(key1 = Unit){
        state.scrollToItem(config.readIndex())
    }

    LaunchedEffect(key1 = state.firstVisibleItemIndex){
        config.writeIndex(state.firstVisibleItemIndex)
        onScroll(playlists.get(state.firstVisibleItemIndex))
    }

//    LaunchedEffect(key1 = state.isScrollInProgress){
//        if (!state.isScrollInProgress && state.firstVisibleItemScrollOffset >= screenWidth / 2){
//            state.animateScrollToItem(state.firstVisibleItemIndex + 1)
//        }
//        else if (!state.isScrollInProgress && state.firstVisibleItemScrollOffset <= screenWidth / 2){
//            state.animateScrollToItem(state.firstVisibleItemIndex)
//        }
//    }


    println("AllPlaylists")

    @OptIn(ExperimentalPagingApi::class)
    HorizontalPager(pageCount = playlists.size) {
        page -> SongList(songs = playlists.get(page).songs, onTap = onTap,
        modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp))
    }

//    LazyRow(
//        state = state,
//        contentPadding = PaddingValues(1.dp),
//        horizontalArrangement = Arrangement.spacedBy(1.dp),
//        modifier = modifier.fillMaxWidth()
//    ){
//        items(playlists){
//                item -> SongList(songs = item.songs, onTap = onTap,
//            modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp))
//        }
//    }
}