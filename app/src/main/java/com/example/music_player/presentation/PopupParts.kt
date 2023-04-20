package com.example.music_player.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.music_player.ui.theme.DarkBlue
import com.example.music_player.ui.theme.DarkGreen
import com.example.music_player.ui.theme.DarkRed

@Composable
fun NameInputPanel(
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
fun ColorPickPanel(
    theme: String,
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
                        onClick = { change_theme(colors_name.get(it))},
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colors.get(it)
                        ),
                        modifier = Modifier
                            .size(36.dp)
                            .border(
                                border =
                                if (theme.equals(colors_name.get(it))) BorderStroke(
                                    3.dp,
                                    Color.DarkGray
                                )
                                else BorderStroke(0.dp, Color.Transparent),
                                CircleShape
                            )
                    ){}
                    Text(text = colors_name.get(it))
                }
            }
        }
    }
}

@Composable
fun BottomPanel(
    onPress: () -> Unit,
    onCancel: () -> Unit,
    color: Color,
    actionName: String,
    modifier: Modifier = Modifier
){
    Surface(
        color = color,
        modifier = modifier.border(border = BorderStroke(2.dp, Color.White), RectangleShape)
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
                Text(text = actionName)
            }
        }
    }
}

