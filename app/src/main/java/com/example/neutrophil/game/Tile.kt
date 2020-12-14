package com.example.neutrophil.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.renderscript.Float2
import com.example.neutrophil.R

class Tile(@Transient private var context : Context, var tileType: Int) {
    var position = Float2(0.0f, 0.0f)
    var onScreen = false
    var discovered = true
    @Transient lateinit var image: Bitmap
    lateinit var directions: List<Boolean>

    init{
        setTile(context)
    }

    fun update(){
        onScreen = (position.x >=0.0f && position.x <= TileGlobals.tileSize * TileGlobals.numHorizontalTiles &&
                position.y >= 0.0f && position.y <= TileGlobals.tileSize * TileGlobals.numVerticalTiles)
    }

    fun draw(canvas: Canvas) {
        if(onScreen && discovered)
            canvas.drawBitmap(image, position.x, position.y, null)
    }

    fun setTile(context: Context) {
        when(tileType) {
            0 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.tile_purple)
                directions = listOf(true, true, true, true)
            }
            1 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.tile_red)
                directions = listOf(false, true, true, true)
            }
            2 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.tile_blue)
                directions = listOf(true, false, true, true)
            }
            3 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.tile_green)
                directions = listOf(true, true, false, true)
            }
            4 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.tile_orange)
                directions = listOf(true, true, true, false)
            }
            5 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.tile_gray)
                directions = listOf(false, false, true, true)
            }
            6 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.tile_plum)
                directions = listOf(false, true, false, true)
            }
            7 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.tile_pink)
                directions = listOf(false, true, true, false)
            }
            8 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.tile_brown)
                directions = listOf(true, false, false, true)
            }
            9 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.tile_aqua)
                directions = listOf(true, false, true, false)
            }
            10 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.tile_yellow)
                directions = listOf(true, true, false, false)
            }
        }
    }
}