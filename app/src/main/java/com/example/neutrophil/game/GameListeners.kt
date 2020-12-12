package com.example.neutrophil.game

class LoopData(){
    var playerTurn = true
    var enemyTurn = false
    var battle = false
}

interface OverWorldListener {
    fun onBattleReady(enemy: Enemy)
    fun onPlayerTurn()
    fun onEnemyTurn()
}

interface BattleListener{
    fun battleOver()
    fun onDeath()
    fun onEnemyTurnEnd()
}