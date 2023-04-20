package com.example.music_player.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.music_player.R

@Composable
fun DropDownChoice(
    currentPlaylist: String,
    addSongs: () -> Unit,
    addPlaylist: () -> Unit,
    deletePlaylist: () -> Unit,
    modifier: Modifier = Modifier
){
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        IconButton(onClick = {if (currentPlaylist != "Local") {expanded = true} else addPlaylist()}) {
            Icon(imageVector = if (currentPlaylist == "Local") Icons.Default.Add else Icons.Default.Edit, contentDescription = "Edit")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(text = "Edit songs") },
                onClick = { addSongs(); expanded = false }
            )
            DropdownMenuItem(
                text = { Text(text = "Add playlist") },
                onClick = { addPlaylist(); expanded = false }
            )
            DropdownMenuItem(
                text = { Text(text = "Delete playlist") },
                onClick = { deletePlaylist(); expanded = false}
            )
        }
    }
}


@Composable
fun SettingsPanel(
    name: String,
    palette: () -> Unit,
    addSongs: () -> Unit,
    createPlaylist: () -> Unit,
    deletePlaylist: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        IconButton(onClick = palette) {
            Icon(painter = painterResource(R.drawable.baseline_palette_24), contentDescription = "Palette")
        }
        Text(text = name, style = MaterialTheme.typography.headlineMedium)
        DropDownChoice(currentPlaylist = name, addSongs, createPlaylist, deletePlaylist)
    }
}