package com.example.neutrophil.game

import android.content.Context
import android.graphics.Canvas

class EnemyManager {
    @Transient var enemies = mutableListOf<Enemy>()
    var numEnemies = 0

    fun draw(canvas: Canvas) {
        for (enemy in enemies) enemy.draw(canvas)
    }

    fun update(){
        for (enemy in enemies) enemy.update()
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