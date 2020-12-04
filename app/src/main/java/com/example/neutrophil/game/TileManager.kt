package com.example.neutrophil.game

import android.content.Context
import android.graphics.Canvas

class TileManager {
    var tiles = mutableListOf<Tile>()

    fun draw(canvas: Canvas) {
        for (tile in tiles) tile.draw(canvas)
    }

    fun update(){
        TODO("Not yet implemented")
    }

    fun setup(context: Context){
        for(i in 0..4)        {
            var tile:Tile = Tile(context)
            tile.setTile(context, i)
            tile.position.x = tile.image.width * i.toFloat()
            tiles.add(tile)
        }

    }
}
