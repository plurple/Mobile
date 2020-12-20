package com.example.neutrophil.game

import android.content.Context
import android.graphics.Canvas

class EnemyManager {
    @Transient var enemies = mutableListOf<Enemy>()
    var numEnemies = 0

    fun draw(canvas: Canvas) {
        for (enemy in enemies) enemy.draw(canvas)
    }

    fun updateMove(tileManager: TileManager) : Boolean{
        var count = 0
        for (enemy in enemies) {
            enemy.updateMove()
            enemy.directions = tileManager.getTileDirections(enemy.position)
            if(enemy.numberSteps == 0) count++
        }
        return count == numEnemies
    }

    fun update(){
        for (enemy in enemies) {
            enemy.update()
        }
    }

    fun rollDice(){
        for (enemy in enemies) enemy.rollDice()
    }

    fun setup(context: Context){
        for (enemy in enemies){
            enemy.setEnemy(context)
        }
    }

}