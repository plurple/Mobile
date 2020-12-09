package com.example.neutrophil.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.renderscript.Float2
import android.renderscript.Int2
import com.example.neutrophil.R

class Player(@Transient private var context : Context){
    var tileOffset = Int2(0,0)
    var maxHealth = 100
    var health = maxHealth
    var numberSteps = 0
    var diceSides = 6
    var numDice = 1
    var position = Float2(0.0f, 0.0f)
    @Transient lateinit var image: Bitmap

    init {
        position = Float2(TileGlobals.tileSize * (TileGlobals.numHorizontalTiles/2), TileGlobals.tileSize*(TileGlobals.numVerticalTiles/2))
        setUp(context)
        rollDice()
    }

    fun setUp(con: Context)
    {
        if(context == null) context = con
        image = BitmapFactory.decodeResource(context.resources, R.drawable.player)
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

    fun checkForOverlap(enemies: MutableList<Enemy>) : Enemy? {
            for (enemy in enemies){
              if(position.x == enemy.position.x && position.y == enemy.position.y){
                  return enemy
              }
            }
        return null
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