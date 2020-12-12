package com.example.neutrophil.game

import android.content.Context
import android.graphics.Canvas
import com.example.neutrophil.SaveManager

class LoopData(){
    var playerTurn = true
    var enemyTurn = false
    var battle = false
}

class OverWorldLoop(var context: Context) : OverWorldListener, BattleListener {
    var player = Player(context)
    var tileManager = TileManager()
    var allEnemies = EnemyManager()
    var battleEnemy = Enemy(context, 0)
    var loopData = LoopData()
    private lateinit var overWorldListener : OverWorldListener
    private lateinit var battleListener : BattleListener

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

    fun battleLateInit(bListener : BattleListener){
        player = SaveManager.loadPlayer()
        player.setUp(context)
        battleEnemy = SaveManager.loadEnemy()
        battleEnemy.setEnemy(context)
        loopData.battle = true
        battleListener = bListener
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
        else{
            if(loopData.enemyTurn && battleEnemy.health != 0){
                battleEnemy.battleUpdate(player)
                loopData.enemyTurn = false
                loopData.playerTurn = true
                if(player.health == 0){
                    onDeath()
                }
                else{
                    onEnemyTurnEnd()
                }
            }
            else {
                if(battleEnemy.health == 0)
                    battleOver()
            }
        }
    }

    fun draw(canvas: Canvas) {
        if(!loopData.battle) {
            tileManager.draw(canvas)
            player.draw(canvas)
            allEnemies.draw(canvas)
        }
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

    override fun battleOver(){
        battleListener.battleOver()
    }

    override fun onDeath(){
        battleListener.onDeath()
    }

    override fun onEnemyTurnEnd(){
        battleListener.onEnemyTurnEnd()
    }
}