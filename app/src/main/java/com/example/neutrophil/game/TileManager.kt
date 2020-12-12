package com.example.neutrophil.game

import android.content.Context
import android.graphics.Canvas
import android.renderscript.Float2

class TileManager {
    var position = Float2(0.0f, 0.0f)
    var numTiles = 0
    @Transient var tiles = mutableListOf<Tile>()

    fun draw(canvas: Canvas) {
        for (tile in tiles) tile.draw(canvas)
    }

    fun setup(context: Context){
        if(tiles.size == 0) {
            for (i in 0 until TileGlobals.numHorizontalTiles + 1) {
                for (j in 0 until TileGlobals.numVerticalTiles + 1) {
                    var tile: Tile = Tile(context, (0..4).random())
                    tile.position = Float2(
                        position.x + TileGlobals.tileSize * i.toFloat(),
                        position.y + TileGlobals.tileSize * j.toFloat()
                    )
                    tiles.add(tile)
                    numTiles++
                }
            }
        }
        else{
            for(tile in tiles)
            {
                tile.setTile(context)
            }
        }
    }
}
