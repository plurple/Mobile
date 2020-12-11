package com.example.neutrophil.game

class EnemyOWAI {
    //TODO("get a more intelligent AI")

    fun moveEnemy(enemy: Enemy){
        var direction = (0..3).random()
        when(direction){
            0 ->{
                enemy.position.y -= TileGlobals.tileSize
            }
            1 ->{
                enemy.position.x += TileGlobals.tileSize
            }
            2 ->{
                enemy.position.y += TileGlobals.tileSize
            }
            3 ->{
                enemy.position.x -= TileGlobals.tileSize
            }
        }
    }
}