package com.example.neutrophil.game

import android.content.Context
import android.graphics.Canvas
import com.example.neutrophil.SaveManager

class OverWorldLoop(var context: Context) {
    var player = Player(context)
    var tileManager = TileManager()


    init {
        player = SaveManager.loadPlayer()
        player.setup()
        tileManager.setup(context)
    }

    fun update() {
    }

    fun draw(canvas: Canvas) {
        tileManager.draw(canvas)
        player.draw(canvas)
    }

    fun pause() {
        TODO("Not yet implemented")
    }

    fun resume() {
        TODO("Not yet implemented")
    }

}