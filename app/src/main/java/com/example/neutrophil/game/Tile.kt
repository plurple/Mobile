package com.example.neutrophil.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.renderscript.Float2
import com.example.neutrophil.R

class Tile(private var context : Context, tileType: Int) {
    var position = Float2(0.0f, 0.0f)
    var onScreen = true
    var discovered = true
    var image: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.tile_blue)

    init{
        setTile(context, tileType)
    }

    fun draw(canvas: Canvas) {
        if(onScreen && discovered)
            canvas.drawBitmap(image, position.x, position.y, null)
    }

    fun update(){
        //TODO("check if player and enemy on the same square")
    }

    fun setTile(context: Context, tileType : Int) {
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