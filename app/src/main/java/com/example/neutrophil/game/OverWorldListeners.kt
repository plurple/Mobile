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
    fun onSetUpUI()
}