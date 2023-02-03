package com.example.music_player

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun SearchBar(
    modifier: Modifier = Modifier
){
    TextField(value = "", onValueChange = {},
    leadingIcon = {
        Icon(imageVector = Icons.Default.Search, contentDescription = null)
    },
    placeholder = {Text(text = "Search")},
    modifier = modifier.fillMaxWidth())
}

@Composable
fun SongPanel(
    modifier: Modifier = Modifier,
    name: String
){
    Surface(
        modifier = modifier.border(1.5.dp, MaterialTheme.colorScheme.secondary, RectangleShape),
        color = MaterialTheme.colorScheme.primary
    ) {
        Row() {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = name, modifier = Modifier.weight(1f), color = MaterialTheme.colorScheme.secondary)
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
                }
            }
        }
    }
}

@Composable
fun SongList(
    modifier: Modifier = Modifier,
    songs: List<String> = List(20){"music$it"}
){
    LazyColumn(modifier = modifier){
        items(songs){
                item -> SongPanel(name = item)
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        SearchBar()
        SongList()
    }
}

//@Preview
//@Composable
//fun PreviewElement(){
//    MainScreen()
//}




