package com.example.music_player


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.music_player.data.Song
import com.example.music_player.domain.Player
import com.example.music_player.domain.nextSong
import com.example.music_player.domain.slideChange
import com.example.music_player.domain.time_format
import kotlinx.coroutines.async


@Composable
fun ControlPanel(
    play_pause: () -> Unit,
    prev: () -> Unit,
    next: () -> Unit,
    isPlaying: Boolean,
    modifier: Modifier = Modifier
){

    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
        IconButton(
            onClick = prev,
            modifier = Modifier.size(80.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.fast_rewind),
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                tint = MaterialTheme.colorScheme.tertiary
            )}
        IconButton(
            onClick = play_pause,
            modifier = Modifier.size(80.dp)
        ) {
            Icon(painter = painterResource(
                id = if (isPlaying) R.drawable.pause_circle_outline else R.drawable.play_circle_outline),
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                tint = MaterialTheme.colorScheme.tertiary
            ) }
        IconButton(
            onClick = next,
            modifier = Modifier.size(80.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.fast_forward),
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                tint = MaterialTheme.colorScheme.tertiary
            ) }
    }
}

@Composable
fun DurationPanel(
    current_time: Float,
    onSliderChange: (Float) -> Unit,
    duration: Float,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier) {
        Slider(
            value = current_time,
            onValueChange = onSliderChange,
            valueRange = 0f..duration,
            colors = SliderDefaults.colors(MaterialTheme.colorScheme.tertiary)
        )
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()){
            Text(text = time_format(current_time))
            for (i in 1..3){
                Text(text = "")
            }
            Text(text = time_format(duration))
        }
    }
}

@Composable
fun StatefullDurationPanel(
    player: Player,
    duration: Float,
    next: () -> Unit,
    modifier: Modifier = Modifier
){
    var current_time by remember { mutableStateOf(0f) }

    LaunchedEffect(key1 = player.uri, block = {
        val slideChangeAsync = async { slideChange({current_time = player.position()}) }
        val nextSongAsync = async { nextSong(player, duration, {current_time = 0f}, next) }
        slideChangeAsync.await()
        nextSongAsync.await()
    })

    DurationPanel(current_time = current_time, onSliderChange = {it -> current_time = it; player.seekTo(it)}, duration = duration, modifier = modifier)
}


@Composable
fun BackgroundPanel(
    modifier: Modifier = Modifier,
    name: String
){
    val screenHeight = LocalConfiguration.current.screenHeightDp

    Box(
        modifier = modifier
            .height((screenHeight - 225).dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = name,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.tertiary,
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}


@Composable
fun PlayerScreen(
    player: Player,
    prev: () -> Unit,
    next: () -> Unit,
    song: Song,
    modifier: Modifier = Modifier
){
    var isPlaying by remember { mutableStateOf(true) }

    if (isPlaying){
        player.play()
    }else{
        player.pause()
    }

    Column(modifier = modifier) {
        BackgroundPanel(name = song.name)
        StatefullDurationPanel(player, song.duration, next)
        Spacer(modifier = Modifier.height(10.dp))
        ControlPanel(play_pause = { isPlaying = !isPlaying }, prev = prev, next = next, isPlaying)
    }
}
