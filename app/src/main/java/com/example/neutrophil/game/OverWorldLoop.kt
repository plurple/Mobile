package com.example.neutrophil.game

import android.content.Context
import android.graphics.Canvas
import com.example.neutrophil.SaveManager



class OverWorldLoop(var context: Context) : OverWorldListener {
    var player = Player(context)
    var tileManager = TileManager()
    var allEnemies = EnemyManager()
    var loopData = LoopData()
    private lateinit var overWorldListener : OverWorldListener

    fun overWorldLateInit(owListener: OverWorldListener) {
        tileManager = SaveManager.loadTiles()
        tileManager.setup(context)
        allEnemies = SaveManager.loadEnemies()
        allEnemies.setup(context)
        player = SaveManager.loadPlayer()
        player.setUp(context)
        loopData = SaveManager.loadLoopData()
        loopData.battle = false
        overWorldListener = owListener
    }

    fun update() {
        if(!loopData.battle) {
            if (player.numberSteps == 0 && loopData.playerTurn) {
                onEnemyTurn()
            }
            var enemy = player.checkForOverlap(allEnemies.enemies)
            if (enemy != null) {
                allEnemies.enemies.remove(enemy)
                allEnemies.numEnemies--
                onBattleReady(enemy)
            }
            if (loopData.enemyTurn) {
                Thread.sleep(250)
                if (allEnemies.update()) {
                    onPlayerTurn()
                }
            }
        }
    }

    fun draw(canvas: Canvas) {
        tileManager.draw(canvas)
        player.draw(canvas)
        allEnemies.draw(canvas)
    }

    override fun onBattleReady(enemy: Enemy) {
        loopData.battle = true
        overWorldListener.onBattleReady(enemy)
    }

    override fun onPlayerTurn() {
        loopData.enemyTurn = false
        overWorldListener.onPlayerTurn()
        allEnemies.rollDice()
    }

    override fun onEnemyTurn() {
        loopData.playerTurn = false
        loopData.enemyTurn = true
        overWorldListener.onEnemyTurn()
    }
}