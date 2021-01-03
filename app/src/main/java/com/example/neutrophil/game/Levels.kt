package com.example.neutrophil.game

import android.renderscript.Int2

object Levels {
    var levels = mutableListOf(levelZero(), levelOne(), levelTwo())

    private fun levelZero() : Level {
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
        level.listOfEnemies = mutableListOf( LevelEnemy(Int2(8, 2)), LevelEnemy(Int2(8, 8)))
        level.playerPos = Int2(3,5)
        level.listOfHealthPotions = mutableListOf(Int2(7,2))
        level.nextLevelPos = Int2(9,9)
        level.nextLevel = 1
        return level
    }

    private fun levelOne() : Level {
        val level = Level()
        level.numCellsX = 20
        level.numCellsY = 22
        level.listOfTiles = mutableListOf(
            7,	1,	1,	1,	1,	1,	5,	0,	0,	0,	0,	7,	1,	1,	1,	1,	1,	1,	1,	5,
            4,	0,	0,	0,	0,	0,	2,	0,	0,	0,	0,	4,	0,	0,	0,	0,	0,	0,	0,	2,
            4,	0,	0,	0,	0,	0,	0,	1,	1,	1,	1,	0,	0,	0,	0,	0,	0,	0,	0,	2,
            4,	0,	0,	0,	0,	0,	0,	3,	3,	3,	3,	0,	0,	0,	0,	0,	0,	0,	0,	2,
            4,	0,	0,	0,	0,	0,	2,	7,	1,	1,	5,	4,	0,	0,	0,	0,	0,	0,	0,	2,
            10,	3,	3,	3,	3,	3,	8,	4,	0,	0,	2,	4,	0,	0,	0,	0,	0,	0,	0,	2,
            0,	0,	7,	1,	1,	1,	5,	4,	0,	0,	2,	10,	3,	3,	3,	0,	0,	0,	0,	2,
            0,	0,	4,	0,	0,	0,	2,	10,	3,	8,	9,	7,	1,	1,	5,	4,	0,	0,	0,	2,
            0,	0,	4,	0,	0,	0,	0,	1,	1,	5,	9,	4,	0,	0,	2,	4,	0,	0,	0,	2,
            0,	0,	4,	0,	0,	0,	0,	0,	0,	2,	9,	4,	0,	0,	2,	10,	3,	3,	0,	8,
            0,	0,	4,	0,	0,	0,	0,	0,	0,	2,	4,	0,	0,	0,	0,	1,	1,	5,	9,	0,
            0,	0,	4,	0,	0,	0,	0,	0,	0,	0,	2,	4,	0,	0,	0,	0,	0,	2,	9,	0,
            0,	0,	4,	0,	0,	0,	0,	0,	0,	2,	9,	4,	0,	0,	0,	0,	0,	2,	9,	0,
            0,	0,	4,	0,	0,	0,	0,	0,	0,	2,	9,	4,	0,	0,	0,	0,	0,	2,	9,	0,
            0,	0,	10,	3,	3,	3,	3,	3,	3,	8,	9,	10,	3,	3,	3,	3,	3,	8,	9,	0,
            0,	0,	7,	1,	1,	1,	1,	1,	1,	1,	0,	1,	1,	1,	1,	1,	1,	1,	0,	5,
            0,	0,	4,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	2,
            0,	0,	4,	0,	0,	0,	0,	0,	0,	3,	3,	3,	0,	0,	0,	0,	0,	0,	0,	2,
            0,	0,	4,	0,	0,	0,	0,	0,	2,	0,	0,	0,	4,	0,	0,	0,	0,	0,	0,	2,
            0,	0,	4,	0,	0,	0,	0,	0,	2,	0,	0,	0,	4,	0,	0,	0,	0,	0,	0,	2,
            0,	0,	4,	0,	0,	0,	0,	0,	2,	0,	0,	0,	4,	0,	0,	0,	0,	0,	0,	2,
            0,	0,	10,	3,	3,	3,	3,	3,	8,	0,	0,	0,	10,	3,	3,	3,	3,	3,	3,	8
        )
        level.listOfEnemies = mutableListOf( LevelEnemy(Int2(5, 3)), LevelEnemy(Int2(15, 2)), LevelEnemy(Int2(16, 6), 4),
            LevelEnemy(Int2(5, 9), 3), LevelEnemy(Int2(4, 12), 6), LevelEnemy(Int2(7, 12), 2),
            LevelEnemy(Int2(13, 12), 0), LevelEnemy(Int2(15, 12), 4), LevelEnemy(Int2(5, 17), 2),
            LevelEnemy(Int2(16, 18), 2))
        level.playerPos = Int2(1,2)
        level.listOfHealthPotions = mutableListOf(Int2(18,1), Int2(8,5), Int2(13,9), Int2(4,8))
        level.nextLevelPos = Int2(3,20)
        level.nextLevel = 2
        return level
    }


    private fun levelTwo() : Level {
        val level = Level()
        level.numCellsX = 15
        level.numCellsY = 13
        level.listOfTiles = mutableListOf(
            7,	1,	1,	1,	1,	5,	0,	0,	0,	7,	1,	1,	1,	1,	5,
            4,	0,	0,	0,	0,	2,	0,	0,	0,	4,	0,	0,	0,	0,	2,
            4,	0,	0,	0,	0,	0,	6,	6,	6,	0,	0,	0,	0,	0,	2,
            4,	0,	0,	0,	0,	2,	0,	0,	0,	4,	0,	0,	0,	0,	2,
            4,	0,	0,	0,	0,	2,	0,	0,	0,	4,	0,	0,	0,	0,	2,
            10,	0,	3,	3,	3,	8,	0,	0,	0,	10,	3,	3,	3,	3,	8,
            0,	9,	0,	0,	0,	0,	0,	0,	0,	7,	1,	1,	1,	1,	5,
            0,	9,	0,	0,	0,	0,	0,	7,	1,	0,	0,	0,	0,	0,	2,
            7,	0,	1,	1,	5,	0,	0,	4,	0,	0,	0,	0,	0,	0,	2,
            4,	0,	0,	0,	2,	0,	0,	4,	0,	0,	0,	0,	0,	0,	2,
            4,	0,	0,	0,	0,	6,	6,	0,	0,	0,	0,	0,	0,	0,	2,
            4,	0,	0,	0,	2,	0,	0,	4,	0,	0,	0,	0,	0,	0,	2,
            10,	3,	3,	3,	8,	0,	0,	10,	3,	3,	3,	3,	3,	3,	8
            )
        level.listOfEnemies = mutableListOf( LevelEnemy(Int2(4, 3), 4), LevelEnemy(Int2(11, 1), 1), LevelEnemy(Int2(12, 4), 7),
            LevelEnemy(Int2(2, 11), 6), LevelEnemy(Int2(10, 8), 10), LevelEnemy(Int2(9, 11), 8))
        level.playerPos = Int2(1,2)
        level.listOfHealthPotions = mutableListOf(Int2(13,1), Int2(12,11))
        level.nextLevelPos = Int2(13,7)
        level.nextLevel = 0
        return level
    }

}