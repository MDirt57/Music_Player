package com.example.music_player.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.music_player.ui.theme.DarkBlue
import com.example.music_player.ui.theme.DarkGreen
import com.example.music_player.ui.theme.DarkRed

@Composable
fun TopPanel(
    on_close: () -> Unit,
    modifier: Modifier = Modifier
){
    Surface(
        color = Color.DarkGray,
        modifier = modifier.width(200.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = on_close,
                modifier = Modifier
                    .padding(4.dp)
                    .background(Color.Red, CircleShape)
                    .size(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    tint = Color.White,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun ContentBox(
    on_close: () -> Unit,
    change_theme: (String) -> Unit,
    modifier: Modifier = Modifier
){
    val colors_name = arrayListOf<String>("Classic", "Red Sky", "Nature")
    val colors = arrayListOf<Color>(DarkBlue, DarkRed, DarkGreen)

    Surface(
        color = Color.White,
        modifier = modifier
            .width(200.dp)
            .height(100.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            (0..2).map {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(
                        onClick = { change_theme(colors_name.get(it)); on_close() },
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colors.get(it)
                        ),
                        modifier = Modifier
                            .size(36.dp)
                    ){}
                    Text(text = colors_name.get(it))
                }
            }
        }
    }
}


@Composable
fun ChangeThemePanel(
    on_close: () -> Unit,
    change_theme: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Popup(
        alignment = Alignment.Center,
        onDismissRequest = {}
    ) {
        Column(modifier = modifier) {
            TopPanel(on_close = on_close)
            ContentBox(on_close = on_close, change_theme = change_theme)
        }
    }
}