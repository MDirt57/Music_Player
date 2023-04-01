package com.example.music_player.data

import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.res.Resources
import android.provider.Settings.Global.getString
import androidx.compose.ui.res.stringResource
import com.example.music_player.R

class Config(context: Context){
    val sharedPref = context.getSharedPreferences("Config", MODE_PRIVATE)
    val writer = sharedPref.edit()

    fun readTheme(): String{
        return sharedPref.getString("theme", "Classic")!!
    }

    fun writeTheme(theme: String){
        writer.putString("theme", theme)
        writer.apply()
    }

    fun readIndex(): Int{
        return sharedPref.getInt("index", 0)
    }

    fun writeIndex(index: Int){
        writer.putInt("index", index)
        writer.apply()
    }
}