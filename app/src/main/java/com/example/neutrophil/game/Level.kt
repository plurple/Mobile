package com.example.neutrophil.game

import android.renderscript.Int2

class Level {
    var numCellsX = 0
    var numCellsY = 0
    var listOfTiles = mutableListOf<Int>()
    var listOfEnemies = mutableListOf<LevelEnemy>()
    var playerPos = Int2(0, 0)
    var nextLevelPos = Int2(0, 0)
    var nextLevel = 0
    var listOfHealthPotions = mutableListOf<Int2>()
}

class LevelEnemy(var position : Int2 = Int2(0,0), var type : Int = 0){
}

