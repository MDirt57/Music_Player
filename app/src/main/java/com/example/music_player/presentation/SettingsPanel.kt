package com.example.music_player.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.music_player.R

@Composable
fun SettingsPanel(
    name: String,
    palette: () -> Unit,
    createPlaylist: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        IconButton(onClick = palette) {
            Icon(painter = painterResource(R.drawable.baseline_palette_24), contentDescription = null)
        }
        Text(text = name, style = MaterialTheme.typography.headlineMedium)
        IconButton(onClick = createPlaylist) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }
}