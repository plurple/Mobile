package com.example.neutrophil.game

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.renderscript.Float2
import com.example.neutrophil.R

class LevelManager {
    var newGame = true
    var currentLevel = 0
    var nextLevel = 0
    var nextLevelPos = Float2(0.0f, 0.0f)
    var onScreen = true

    fun update(){
        onScreen = (nextLevelPos.x >=0.0f && nextLevelPos.x <= TileGlobals.tileSize * TileGlobals.numHorizontalTiles &&
                nextLevelPos.y >= 0.0f && nextLevelPos.y <= TileGlobals.tileSize * TileGlobals.numVerticalTiles)
    }

    fun draw(context: Context, canvas: Canvas){
        if(onScreen)
            canvas.drawBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.portal), nextLevelPos.x, nextLevelPos.y, null)
    }
}