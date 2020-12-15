package com.example.neutrophil.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.renderscript.Float2
import com.example.neutrophil.R

class Item(@Transient private var context : Context) {
    var position = Float2(0.0f, 0.0f)
    var onScreen = false
    var itemType = 0
    @Transient lateinit var image: Bitmap

    init{
        setItem(context)
        position = Float2(TileGlobals.tileSize * (0 until TileGlobals.numHorizontalTiles).random(), TileGlobals.tileSize * (0 until TileGlobals.numVerticalTiles).random())
    }

    fun update(){
        onScreen = (position.x >=0.0f && position.x <= TileGlobals.tileSize * TileGlobals.numHorizontalTiles &&
                position.y >= 0.0f && position.y <= TileGlobals.tileSize * TileGlobals.numVerticalTiles)
    }

    fun draw(canvas: Canvas) {
        if(onScreen)
            canvas.drawBitmap(image, position.x, position.y, null)
    }

    fun setItem(context: Context) {
        when(itemType){
            0 -> image = BitmapFactory.decodeResource(context.resources, R.drawable.health)
        }
    }

    fun pickUp(player: Player){
        when(itemType){
            0 -> player.numHealthPotions++
        }
    }
}