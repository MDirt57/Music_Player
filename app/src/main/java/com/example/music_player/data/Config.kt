package com.example.music_player.data

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.provider.Settings.Global.getString
import androidx.compose.ui.res.stringResource
import com.example.music_player.R

class Config(activity: Activity, resources: Resources, context: Context){
    val activity = activity
    val resources = resources
    val context = context
    fun open(){
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
    }

    fun write(theme: String){
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()){
            putString(context.getString(R.string.theme), theme)
            apply()
        }
    }

    fun read(): String?{
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        val defaultValue = resources.getString(R.string.theme)
        val theme = sharedPref.getString(context.getString(R.string.theme), defaultValue)
        return theme
    }
}