package com.example.music_player.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup

@Composable
fun CreatePlaylistPanel(
    onPress: (String) -> Unit,
    onCancel: () -> Unit,
    color: Color,
    modifier: Modifier = Modifier
){
    Popup(
        alignment = Alignment.Center,
        onDismissRequest = {}
    ) {
        var text by remember { mutableStateOf("") }
        Column(
            modifier = modifier.border(border = BorderStroke(2.dp, Color.White), RectangleShape )
        ){
            NameInputPanel(text = text, onTyping = {newtext -> text = newtext}, color = color, modifier = modifier.width(250.dp))
            BottomPanel(onPress = {onPress(text)}, onCancel = onCancel, color = color, actionName = "Create", modifier = modifier.width(250.dp))
        }
    }
}

@Preview
@Composable
fun Preview(){
    CreatePlaylistPanel(onPress = {}, onCancel = {}, color = Color.Black)
}