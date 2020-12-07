package com.example.neutrophil.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.renderscript.Float2
import com.example.neutrophil.R

class Player(private val context : Context) {
    var maxHealth = 100;
    var health = 65;
    var numberSteps = 6;
    var diceSides = 6;
    var numDice = 1;
    var position = Float2(0.0f, 0.0f)
    var image: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.player)

    fun setup() {
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

    fun moveUp(){
        if(numberSteps > 0) {
            position.y -= image.height
            numberSteps--
        }
    }

    fun moveDown(){
        if(numberSteps > 0) {
            position.y += image.height
            numberSteps--
        }
    }

    fun moveLeft(){
        if(numberSteps > 0) {
            position.x -= image.width
            numberSteps--
        }
    }

    fun moveRight(){
        if(numberSteps > 0) {
            position.x += image.width
            numberSteps--
        }
    }
}