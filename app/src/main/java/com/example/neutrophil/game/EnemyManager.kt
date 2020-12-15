package com.example.neutrophil.game

import android.content.Context
import android.graphics.Canvas

class EnemyManager {
    @Transient var enemies = mutableListOf<Enemy>()
    var numEnemies = 0

    fun draw(canvas: Canvas) {
        for (enemy in enemies) enemy.draw(canvas)
    }

    fun update(tileManager: TileManager) : Boolean{
        var count = 0
        for (enemy in enemies) {
            enemy.update()
            enemy.directions = tileManager.getTileDirections(enemy.position)
            if(enemy.numberSteps == 0) count++
        }
        return count == numEnemies
    }

    fun rollDice(){
        for (enemy in enemies) enemy.rollDice()
    }

    fun setup(context: Context){
        if(enemies.size == 0) {
            for (i in 0..9) {
                var enemy: Enemy = Enemy(context, (0..4).random())
                enemies.add(enemy)
                numEnemies++
            }
        }
        else{
            for (enemy in enemies){
                enemy.setEnemy(context)
            }
        }
    }
}