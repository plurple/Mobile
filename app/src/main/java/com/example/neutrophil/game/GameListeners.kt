package com.example.neutrophil.game

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