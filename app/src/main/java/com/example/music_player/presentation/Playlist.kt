package com.example.music_player.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIconDefaults.Text
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.music_player.R
import com.example.music_player.SongList
import com.example.music_player.SongPanel
import com.example.music_player.data.Playlist
import com.example.music_player.data.Song
import kotlinx.coroutines.flow.filter

@Composable
fun PlaylistUI(
    playlists: List<Playlist>,
    onTap: (Song) -> Unit,
    modifier: Modifier = Modifier
){
    val state = rememberLazyListState()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = if (state.firstVisibleItemIndex == 0) "Local" else "Network", style = MaterialTheme.typography.headlineMedium)
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