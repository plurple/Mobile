package com.example.neutrophil.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.renderscript.Float2
import com.example.neutrophil.R

class Tile(@Transient private var context : Context, var tileType: Int) {
    var position = Float2(0.0f, 0.0f)
    var onScreen = true
    var discovered = true
    @Transient lateinit var image: Bitmap

    init{
        setTile(context)
    }

    fun draw(canvas: Canvas) {
        if(onScreen && discovered)
            canvas.drawBitmap(image, position.x, position.y, null)
    }

    fun setTile(context: Context) {
        when(tileType) {
            0 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.tile_blue)
            }
            1 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.tile_red)
            }
            2 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.tile_orange)
            }
            3 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.tile_green)
            }
            4 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.tile_purple)
            }
        }
    }
}