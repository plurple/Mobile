package com.example.neutrophil.game

import android.content.Context
import android.graphics.Canvas
import com.example.neutrophil.SaveManager


class BattleLoop(var context: Context) : BattleListener {
    var player = Player(context)
    var battleEnemy = Enemy(context, 0)
    var loopData = LoopData()
    private lateinit var battleListener : BattleListener


    fun battleLateInit(bListener : BattleListener){
        player = SaveManager.loadPlayer()
        player.setUp(context)
        battleEnemy = SaveManager.loadEnemy()
        battleEnemy.setEnemy(context)
        battleListener = bListener

    }

    fun update() {
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

    fun draw(canvas: Canvas) {
        player.battleDraw(canvas)
        battleEnemy.battleDraw(canvas)
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