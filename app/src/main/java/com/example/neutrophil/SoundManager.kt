package com.example.neutrophil

import android.content.Context
import android.media.MediaPlayer

object SoundManager {
    private var musicPlayer : MediaPlayer = MediaPlayer()
    var musicLevel : Float = 0.5f
        set(value){
            field = value
            musicPlayer.setVolume(value, value)
        }
    var soundsLevel : Float = 0.5f
        set(value){
            field = value
            //soundsPlayer.setVolume(value, value)
        }

    fun playMusic(context: Context, musicFile: Int) {
        val player = MediaPlayer.create(context, musicFile)
        musicPlayer = player
        player.isLooping = true
        player.setVolume(musicLevel, musicLevel)
        player.start()
    }

}