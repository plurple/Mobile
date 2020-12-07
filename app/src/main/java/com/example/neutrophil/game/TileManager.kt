package com.example.neutrophil.game

import android.content.Context
import android.graphics.Canvas
import android.renderscript.Float2

class TileManager {
    var position = Float2(0.0f, 0.0f)
    var tiles = mutableListOf<Tile>()

    fun draw(canvas: Canvas) {
        for (tile in tiles) tile.draw(canvas)
    }

    fun update(){
        for (tile in tiles) tile.update()
    }

    fun setup(context: Context){
        for(i in 0..TileGlobals.numHorizontalTiles+1) {
            for (j in 0..TileGlobals.numVerticalTiles+1) {
                var tile: Tile = Tile(context)
                tile.setTile(context, (0..4).random())
                tile.position = Float2(position.x + TileGlobals.tileSize * i.toFloat(), position.y + TileGlobals.tileSize * j.toFloat())
                tiles.add(tile)
            }
        }
    }
}
