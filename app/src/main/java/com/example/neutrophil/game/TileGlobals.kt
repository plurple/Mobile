package com.example.neutrophil.game

import android.content.res.Resources

object TileGlobals {
    var screenWidth = Resources.getSystem().displayMetrics.widthPixels
    var screenHeight = Resources.getSystem().displayMetrics.heightPixels
    var tileSize = 64.0f * Resources.getSystem().displayMetrics.density
    var numHorizontalTiles = (screenWidth/ tileSize).toInt()
    var numVerticalTiles = (screenHeight/ tileSize).toInt()

    init {
        if(screenHeight > screenWidth)
        {
            var temp = screenWidth
            screenWidth = screenHeight
            screenHeight = temp
            temp = numVerticalTiles
            numVerticalTiles = numHorizontalTiles
            numHorizontalTiles = temp
        }
    }
}