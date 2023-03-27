package com.example.music_player.domain

interface Config {
    fun createConfig()
    fun writeTheme()
    fun readTheme()
    fun writePlaylists()
    fun readPlaylists()
}