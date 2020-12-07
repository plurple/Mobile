package com.example.neutrophil.game

import android.content.Context
import android.graphics.BitmapFactory
import android.renderscript.Float2
import android.renderscript.Int2
import com.example.neutrophil.R

class Player(private val context : Context) : Entity(context) {
    var tileOffset = Int2(0,0)


    init {
        position = Float2(TileGlobals.tileSize * (TileGlobals.numHorizontalTiles/2), TileGlobals.tileSize*(TileGlobals.numVerticalTiles/2))
        image = BitmapFactory.decodeResource(context.resources, R.drawable.player)
        rollDice()
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