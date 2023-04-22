package com.example.music_player.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties

@Composable
fun CreatePlaylistPanel(
    onPress: (String) -> Unit,
    onCancel: () -> Unit,
    color: Color,
    modifier: Modifier = Modifier
){
    var text by remember { mutableStateOf("") }
    if (text.length > 14){
        text = text.slice(0..14)
    }
    Popup(
        properties = PopupProperties(focusable = true),
        alignment = Alignment.Center,
        onDismissRequest = {}
    ) {
        Column(
            modifier = modifier.border(border = BorderStroke(2.dp, Color.White), RectangleShape )
        ){
            NameInputPanel(text = text, onTyping = {newtext -> text = newtext}, color = color, modifier = modifier.width(250.dp))
            BottomPanel(onPress = {onPress(text); onCancel()}, onCancel = onCancel, color = color, actionName = "Create", modifier = modifier.width(250.dp))
        }
    }
}

@Preview
@Composable
fun Preview(){
    CreatePlaylistPanel(onPress = {}, onCancel = {}, color = Color.Black)
}