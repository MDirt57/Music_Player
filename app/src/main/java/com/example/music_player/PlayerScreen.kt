package com.example.music_player


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.music_player.ui.theme.Music_PlayerTheme
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


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
    player: PlayerActivity,
    duration: Float,
    isPlaying: Boolean,
    modifier: Modifier = Modifier
){
    var current_time by remember { mutableStateOf(0f) }

    LaunchedEffect(key1 = isPlaying, block = {
        slideChange(player, {current_time += 1000f})
    })

    Column(modifier = modifier) {
        Slider(
            value = current_time,
            onValueChange = {current_time = it; player.seekTo(it)},
            valueRange = 0f..duration
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

fun time_format(milliseconds: Float): String{
    val sec = milliseconds/1000
    val minutes = (sec/60).toInt()
    val seconds = (sec - 60*minutes).toInt()
    return "${if (minutes>=10) minutes else "0$minutes"}:${if (seconds>=10) seconds else "0$seconds"}"
}


@Composable
fun BackgroundPanel(
    modifier: Modifier = Modifier,
    name: String
){
    Box(
        modifier = modifier
            .height(550.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = name,
            color = MaterialTheme.colorScheme.tertiary,
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}

suspend fun slideChange(
    player: PlayerActivity,
    perSecond: () -> Unit
){
    while(player.isPlaying()){
        delay(1000L)
        perSecond()
    }
}


@Composable
fun PlayerScreen(
    player: PlayerActivity,
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
        DurationPanel(player, song.duration, isPlaying)
        Spacer(modifier = Modifier.height(10.dp))
        ControlPanel(play_pause = { isPlaying = !isPlaying }, prev = prev, next = next, isPlaying)
    }
}
