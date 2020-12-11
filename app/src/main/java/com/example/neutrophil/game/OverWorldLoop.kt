package com.example.neutrophil.game

import android.content.Context
import android.graphics.Canvas
import com.example.neutrophil.SaveManager

class LoopData(){
    var playerTurn = true
    var enemyTurn = false
    var battle = false
}

class OverWorldLoop(var context: Context) : BattleListener{
    var player = Player(context)
    var tileManager = TileManager()
    var enemyManager = EnemyManager()
    var loopData = LoopData()
    private lateinit var battleListener : BattleListener

    fun lateInit(bListener: BattleListener) {
        tileManager = SaveManager.loadTiles()
        tileManager.setup(context)
        enemyManager = SaveManager.loadEnemies()
        enemyManager.setup(context)
        player = SaveManager.loadPlayer()
        player.setUp(context)
        loopData = SaveManager.loadLoopData()
        loopData.battle = false
        battleListener = bListener
    }

    fun update() {
        if(!loopData.battle) {
            if (player.numberSteps == 0 && loopData.playerTurn) {
                onEnemyTurn()
            }
            var enemy = player.checkForOverlap(enemyManager.enemies)
            if (enemy != null) {
                enemyManager.enemies.remove(enemy)
                enemyManager.numEnemies--
                onBattleReady(enemy)
            }
            tileManager.update()
            if (loopData.enemyTurn) {
                Thread.sleep(150)
                if (enemyManager.update()) {
                    onPlayerTurn()
                }
            }
        }
    }

    fun draw(canvas: Canvas) {
        if(!loopData.battle) {
            tileManager.draw(canvas)
            player.draw(canvas)
            enemyManager.draw(canvas)
        }
    }

    override fun onBattleReady(enemy: Enemy) {
        loopData.battle = true
        battleListener.onBattleReady(enemy)
    }

    override fun onPlayerTurn() {
        loopData.enemyTurn = false
        battleListener.onPlayerTurn()
        enemyManager.rollDice()
    }

    override fun onEnemyTurn() {
        loopData.playerTurn = false
        loopData.enemyTurn = true
        battleListener.onEnemyTurn()
    }
}