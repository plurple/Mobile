package com.example.neutrophil.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.renderscript.Float2

abstract class Entity(private val context : Context) {
    var maxHealth = 100;
    var health = 65;
    var numberSteps = 0;
    var diceSides = 6;
    var numDice = 1;
    var position = Float2(0.0f, 0.0f)
    lateinit var image: Bitmap

    fun update() {

    }

    fun draw(canvas: Canvas) {
        canvas.drawBitmap(image, position.x, position.y,null )
    }

    fun rollDice(){
        if(numberSteps <= 0) {
            for (i in 1..numDice) {
                numberSteps += (1..diceSides).random()
            }
        }
    }
}