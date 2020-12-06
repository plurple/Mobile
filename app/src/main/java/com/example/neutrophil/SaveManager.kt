package com.example.neutrophil

import android.content.Context
import android.content.SharedPreferences
import com.example.neutrophil.game.Player
import com.example.neutrophil.menus.Settings
import com.google.gson.Gson

object SaveManager{
    private lateinit var context :Context
    private lateinit var sharedPref :SharedPreferences

    fun addContext(applicationContext: Context) {
        context = applicationContext
        sharedPref = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
    }

    fun savePlayer(player : Player)
    {
        with (sharedPref.edit()) {
            putString(context.getString(R.string.savedPlayer), Gson().toJson(player))
            apply()
        }
    }

    fun loadPlayer() : Player
    {
        val savedPlayer = sharedPref.getString(context.getString(R.string.savedPlayer), "")
        if(savedPlayer!!.isEmpty()) return Player(context)
        return Gson().fromJson(savedPlayer, Player::class.java)
    }

    fun saveSettings(settings : Settings)
    {
        with (sharedPref.edit()) {
            putString(context.getString(R.string.savedSettings), Gson().toJson(settings))
            apply()
        }
    }

    fun loadSettings() : Settings
    {
        val savedSetings = sharedPref.getString(context.getString(R.string.savedSettings), "")
        if(savedSetings!!.isEmpty()) return Settings()
        return Gson().fromJson(savedSetings, Settings::class.java)
    }
}