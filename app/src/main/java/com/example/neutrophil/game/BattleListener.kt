package com.example.neutrophil.game

interface BattleListener {
    fun onBattleReady(enemy: Enemy)
    fun onPlayerTurn()
    fun onEnemyTurn()
}