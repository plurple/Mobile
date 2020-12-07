package com.example.neutrophil.game

import android.content.res.Resources

object TileGlobals {
    var tileSize = 64.0f * Resources.getSystem().displayMetrics.density
    var numHorizontalTiles = 12 //TODO(make this variable for screen size
    var numVerticalTiles = 7 //TODO(make this variable for screen size
}