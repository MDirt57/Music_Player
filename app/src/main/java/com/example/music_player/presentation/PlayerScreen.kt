package com.example.music_player


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.music_player.data.Song
import com.example.music_player.domain.Player
import com.example.music_player.domain.PlayerActivity
import kotlinx.coroutines.*


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
    player: Player,
    duration: Float,
    next: () -> Unit,
    isPlaying: Boolean,
    modifier: Modifier = Modifier
){
    var current_time by remember { mutableStateOf(0f) }

    if (player.position() == 0f){
        current_time = 0f
    }else if (player.position() >= duration){
        next()
    }

//    LaunchedEffect(key1 = Unit, block = {
//        slideChange(player, {current_time += 1000f})
//    })
    LaunchedEffect(key1 = player.id, block = {
        slideChange2({current_time = player.position()})
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
            .height(525.dp)
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
    player: Player,
    perSecond: () -> Unit
){
    while(player.isPlaying()){
        delay(1000L)
        perSecond()
    }
}

suspend fun slideChange2(
    perSecond: () -> Unit
){
    while(true){
        delay(1000L)
        perSecond()
    }
}


@Composable
fun PlayerScreen(
    viewmodel: PlayerActivity,
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
        DurationPanel(player, song.duration, next, isPlaying)
        Spacer(modifier = Modifier.height(10.dp))
        ControlPanel(play_pause = { isPlaying = !isPlaying }, prev = prev, next = next, isPlaying)
    }
}