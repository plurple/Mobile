package com.example.neutrophil.game

class EnemyOWAI {
    //TODO("get a more intelligent AI")

    fun moveEnemy(enemy: Enemy){
        var pickDirection = true
        var directions = mutableListOf(0,1,2,3)
        var direction = (directions).random()

        while(pickDirection) {
            if(enemy.directions[direction])
                pickDirection = false
            else {
                directions.remove(direction)
                direction = (directions).random()
            }
        }
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