package com.example.neutrophil.game

import android.content.Context
import android.graphics.Canvas
import android.renderscript.Float2

class TileManager {
    var position = Float2(0.0f, 0.0f)
    var numTiles = 0
    @Transient var tiles = mutableListOf<Tile>()

    fun update(){
        for (tile in tiles) tile.update()
    }

    fun draw(canvas: Canvas) {
        for (tile in tiles) tile.draw(canvas)
    }

    fun setup(context: Context){
        for(tile in tiles)
        {
            tile.setTile(context)
        }
    }


    fun getTileDirections(position : Float2) : List<Boolean>{
        for(tile in tiles){
            if(tile.position.x == position.x && tile.position.y == position.y) {
                return tile.directions
            }
        }
        return listOf(true, true, true, true)
    }
}
