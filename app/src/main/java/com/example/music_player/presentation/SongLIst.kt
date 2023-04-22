package com.example.music_player.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.music_player.data.Config
import com.example.music_player.data.Song

@Composable
fun SongPanel(
    modifier: Modifier = Modifier,
    name: String,
    icon: () -> Boolean,
    onTap: () -> Unit
){
    var check by remember {mutableStateOf(icon())}

    Surface(
        modifier = modifier.border(1.5.dp, MaterialTheme.colorScheme.secondary, RectangleShape),
        color = MaterialTheme.colorScheme.primary
    ) {
        Row(modifier = Modifier.heightIn(min = 60.dp), verticalAlignment = Alignment.CenterVertically) {
            Button(
                onClick = {onTap(); check = icon()},
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = name,
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.secondary
                )
                if (check){
                    Icon(imageVector = Icons.Default.Done, contentDescription = null)
                }
            }
        }
    }
}


@Composable
fun SongList(
    config: Config,
    songs: List<Song>,
    onTap: (Song) -> Unit,
    icon: (Song) -> Boolean = {false},
    modifier: Modifier = Modifier
){
    val state = rememberLazyListState()

    LaunchedEffect(key1 = Unit){
        state.scrollToItem(config.readIndex(1))
    }

    LaunchedEffect(key1 = state.firstVisibleItemIndex){
        config.writeIndex(1, state.firstVisibleItemIndex)
    }

    LazyColumn(
        state = state,
        modifier = modifier
    ){
        items(songs){
                item -> SongPanel(name = item.name, onTap = { onTap(item) }, icon = { icon(item) })
        }
    }
}