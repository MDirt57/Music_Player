package com.example.music_player

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.music_player.ui.theme.Music_PlayerTheme

@Composable
fun ControlPanel(modifier: Modifier = Modifier){
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.size(80.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.fast_rewind),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )}
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.size(80.dp)
        ) {
            Icon(painter = painterResource(
                id = R.drawable.play_circle_outline),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            ) }
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.size(80.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.fast_forward),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            ) }
    }
}

@Composable
fun DurationPanel(modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        Slider(value = 0f, onValueChange = {})
        Row(horizontalArrangement = Arrangement.SpaceEvenly){
            Text(text = "0:00", modifier = Modifier.weight(1f))
            Text(text = "3:00")
        }
    }
}

@Composable
fun BackgroundPanel(modifier: Modifier = Modifier){
    Text(
        text = "song_name",
        style = MaterialTheme.typography.headlineMedium,
    )
}


@Composable
fun PlayerScreen(modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        BackgroundPanel()
        DurationPanel()
        ControlPanel()
    }
}

@Preview
@Composable
fun PreviewElement2(){
    Music_PlayerTheme {
        BackgroundPanel()
    }
}