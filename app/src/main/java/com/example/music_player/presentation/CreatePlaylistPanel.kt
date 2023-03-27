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
fun ContentPanel(
    text: String,
    onTyping: (String) -> Unit,
    color: Color,
    modifier: Modifier = Modifier
){
    Surface(
        color = color,
        modifier = modifier
    ){
        Column(){
            Text(text = "Name", modifier = Modifier.padding(8.dp))
            TextField(
                value = text,
                onValueChange = onTyping,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    textColor = Color.White,
                    cursorColor = Color.White
                )
            )
        }
    }
}


@Composable
fun BottomPanel(
    onPress: () -> Unit,
    onCancel: () -> Unit,
    color: Color,
    modifier: Modifier = Modifier
){
    Surface(
        color = color,
        modifier = modifier
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceAround
        ){
            Button(
                onClick = onCancel,
                shape = RectangleShape,
                modifier = Modifier.background(Color.Transparent),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Cancel")
            }
            Button(
                onClick = onPress,
                shape = RectangleShape,
                modifier = Modifier.background(Color.Transparent),
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Create")
            }
        }
    }
}

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
            ContentPanel(text = text, onTyping = {newtext -> text = newtext}, color = color, modifier = modifier.width(250.dp))
            BottomPanel(onPress = {onPress(text)}, onCancel = onCancel, color = color, modifier = modifier.width(250.dp))
        }
    }
}

@Preview
@Composable
fun Preview(){
    CreatePlaylistPanel(onPress = {}, onCancel = {}, color = Color.Black)
}