package com.example.neutrophil.game

import android.content.Context
import android.graphics.BitmapFactory
import android.renderscript.Float2
import com.example.neutrophil.R

class Enemy(context: Context, enemyType : Int) : Entity(context){
    init{
        setEnemy(context, enemyType)
        rollDice()
        position = Float2(TileGlobals.tileSize * (0 until TileGlobals.numHorizontalTiles).random(), TileGlobals.tileSize * (0 until TileGlobals.numVerticalTiles).random())
    }

    fun setEnemy(context: Context, enemyType: Int) {
        when(enemyType) {
            0 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.bean)
            }
            1 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.cucumber)
            }
            2 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.green_tadpol)
            }
            3 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.handy)
            }
            4 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.jellyfish)
            }
        }
    }

}