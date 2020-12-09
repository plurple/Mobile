package com.example.neutrophil.game

import android.content.Context
import android.graphics.Canvas
import com.example.neutrophil.SaveManager

class OverWorldLoop(var context: Context) : BattleListener{
    var player = Player(context)
    var tileManager = TileManager()
    var enemyManager = EnemyManager()
    private lateinit var battleListener : BattleListener

    fun lateInit(bListener: BattleListener) {
        tileManager = SaveManager.loadTiles()
        tileManager.setup(context)
        enemyManager = SaveManager.loadEnemies()
        enemyManager.setup(context)
        player = SaveManager.loadPlayer()
        player.setUp(context)
        battleListener = bListener
    }

    fun update() {
        var enemy = player.checkForOverlap(enemyManager.enemies)
        if(enemy != null)
        {
            enemyManager.enemies.remove(enemy)
            enemyManager.numEnemies--
            onBattleReady(enemy)
        }
        tileManager.update()
        enemyManager.update()
    }

    fun draw(canvas: Canvas) {
        tileManager.draw(canvas)
        player.draw(canvas)
        enemyManager.draw(canvas)
    }

    override fun onBattleReady(enemy: Enemy) {
        battleListener.onBattleReady(enemy)
    }
}