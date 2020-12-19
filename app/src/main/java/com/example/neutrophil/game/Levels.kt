package com.example.neutrophil.game

import android.renderscript.Int2

object Levels {
    fun LevelOne() : Level {
        val level = Level()
        level.numCellsX = 10
        level.numCellsY = 10
        level.listOfTiles = mutableListOf(
            7,	1,	1,	1,	1,	1,	1,	1,	1,	5,
            4,	0,	0,	0,	0,	0,	0,	0,	0,	2,
            4,	0,	0,	0,	0,	0,	0,	0,	0,	2,
            4,	0,	0,	0,	0,	0,	0,	0,	0,	2,
            4,	0,	0,	0,	0,	0,	0,	0,	0,	2,
            4,	0,	0,	0,	0,	0,	0,	0,	0,	2,
            4,	0,	0,	0,	0,	0,	0,	0,	0,	2,
            4,	0,	0,	0,	0,	0,	0,	0,	0,	2,
            4,	0,	0,	0,	0,	0,	0,	0,	0,	2,
            10,	3,	3,	3,	3,	3,	3,	3,	3,	8)
        level.listOfEnemies = mutableListOf( LevelEnemy(Int2(9, 2)), LevelEnemy(Int2(9, 9)))
        level.playerPos = Int2(3,5)
        level.listOfHealthPotions = mutableListOf(Int2(7,2))
        return level
    }

}