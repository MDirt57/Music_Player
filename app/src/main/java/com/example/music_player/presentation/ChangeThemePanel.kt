package com.example.music_player.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.music_player.ui.theme.DarkBlue
import com.example.music_player.ui.theme.DarkGreen
import com.example.music_player.ui.theme.DarkRed


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
        var theme by remember {mutableStateOf("")}
        Column(modifier = modifier.border(border = BorderStroke(2.dp, Color.White), RectangleShape)) {
            ColorPickPanel(theme = theme, change_theme = {choice -> theme = choice}, modifier = Modifier.width(250.dp))
            BottomPanel(
                onPress = { change_theme(theme); on_close() },
                onCancel = on_close,
                color = MaterialTheme.colorScheme.primary,
                actionName = "Change",
                modifier = Modifier.width(250.dp))
        }
    }
}