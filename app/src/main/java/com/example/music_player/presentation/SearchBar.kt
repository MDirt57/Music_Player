package com.example.music_player.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.input.ImeAction

@Composable
fun SearchBar(
    tempText: String,
    onTyping: (String) -> Unit,
    onPress: (String) -> Unit,
    focusManager: FocusManager,
    modifier: Modifier = Modifier
){
    TextField(value = tempText, onValueChange = onTyping,
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        placeholder = { Text(text = "Search") },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            onPress(tempText)
            focusManager.clearFocus()
        }),
        singleLine = true,
        modifier = modifier.fillMaxWidth())
}

@Composable
fun StatefullSearchBar(
    text: String,
    onPress: (String) -> Unit,
    focusManager: FocusManager,
    modifier: Modifier = Modifier
){
    var tempText by remember { mutableStateOf(text) }
    SearchBar(
        tempText = tempText,
        onTyping = {newtext -> tempText = newtext},
        onPress = onPress,
        focusManager = focusManager,
        modifier = modifier
    )
}
