package com.example.neutrophil.game

import android.content.Context
import android.graphics.Canvas

class EnemyManager {
    var enemies = mutableListOf<Enemy>()

    fun draw(canvas: Canvas) {
        for (enemy in enemies) enemy.draw(canvas)
    }

    fun update(){
        for (enemy in enemies) enemy.update()
    }

    fun setup(context: Context){
        for(i in 0..9) {
            var enemy: Enemy = Enemy(context, (0..4).random())
            enemies.add(enemy)
        }
    }
}