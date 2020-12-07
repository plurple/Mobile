package com.example.neutrophil.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.renderscript.Float2
import android.renderscript.Int2
import com.example.neutrophil.R

class Player(private val context : Context) {
    var maxHealth = 100;
    var health = 65;
    var numberSteps = 6;
    var diceSides = 6;
    var numDice = 1;
    var position = Float2(TileGlobals.tileSize * (TileGlobals.numHorizontalTiles/2), TileGlobals.tileSize*(TileGlobals.numVerticalTiles/2))
    var tileOffset = Int2(0,0)
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
            position.y -= TileGlobals.tileSize
            tileOffset.y--
            numberSteps--
        }
    }

    fun moveDown(){
        if(numberSteps > 0) {
            position.y += TileGlobals.tileSize
            tileOffset.y++
            numberSteps--
        }
    }

    fun moveLeft(){
        if(numberSteps > 0) {
            position.x -= TileGlobals.tileSize
            tileOffset.x--
            numberSteps--
        }
    }

    fun moveRight(){
        if(numberSteps > 0) {
            position.x += TileGlobals.tileSize
            tileOffset.x++
            numberSteps--
        }
    }
}