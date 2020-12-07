package com.example.neutrophil.game

import android.content.Context
import android.graphics.Canvas
import com.example.neutrophil.SaveManager

class OverWorldLoop(var context: Context) {
    var player = Player(context)
    var tileManager = TileManager()
    var enemyManager = EnemyManager()

    init {
        player = SaveManager.loadPlayer()
        tileManager.setup(context)
        enemyManager.setup(context)
    }

    fun update() {
        tileManager.update()
        enemyManager.update()
    }

    fun draw(canvas: Canvas) {
        tileManager.draw(canvas)
        player.draw(canvas)
        enemyManager.draw(canvas)
    }
}