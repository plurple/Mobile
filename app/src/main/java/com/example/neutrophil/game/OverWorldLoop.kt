package com.example.neutrophil.game

import android.content.Context
import android.graphics.Canvas
import android.renderscript.Float2
import android.renderscript.Int2
import com.example.neutrophil.SaveManager



class OverWorldLoop(var context: Context) : OverWorldListener {
    var player = Player(context)
    var tileManager = TileManager()
    var allEnemies = EnemyManager()
    var loopData = LoopData()
    private lateinit var overWorldListener : OverWorldListener

    fun overWorldLateInit(owListener: OverWorldListener) {
        tileManager = SaveManager.loadTiles()
        tileManager.setup(context)
        allEnemies = SaveManager.loadEnemies()
        allEnemies.setup(context)
        player = SaveManager.loadPlayer()
        player.setUp(context)
        player.directions = tileManager.getTileDirections(player.position)
        for(enemy in allEnemies.enemies) enemy.directions = tileManager.getTileDirections(enemy.position)
        loopData = SaveManager.loadLoopData()
        loopData.battle = false
        overWorldListener = owListener
    }

    fun update() {
        if(!loopData.battle) {
            if(player.position.x < 0.0f || player.position.x > TileGlobals.tileSize * TileGlobals.numHorizontalTiles ||
                player.position.y < 0.0f || player.position.y > TileGlobals.tileSize * TileGlobals.numVerticalTiles)
                recenterPlayer()
            if (player.numberSteps == 0 && loopData.playerTurn) {
                onEnemyTurn()
            }
            var enemy = player.checkForOverlap(allEnemies.enemies)
            if (enemy != null) {
                allEnemies.enemies.remove(enemy)
                allEnemies.numEnemies--
                onBattleReady(enemy)
            }
            tileManager.update()
            if (loopData.enemyTurn) {
                Thread.sleep(250)
                if (allEnemies.update(tileManager)) {
                    onPlayerTurn()
                }
            }
        }
    }

    fun draw(canvas: Canvas) {
        tileManager.draw(canvas)
        player.draw(canvas)
        allEnemies.draw(canvas)
    }

    fun recenterPlayer(){
        var adjustment = Float2(player.tileOffset.x * TileGlobals.tileSize, player.tileOffset.y * TileGlobals.tileSize)
        player.position -= adjustment
        for(tile in tileManager.tiles) tile.position -= adjustment
        for(enemy in allEnemies.enemies) enemy.position -= adjustment
        player.tileOffset = Int2(0, 0)
    }

    override fun onBattleReady(enemy: Enemy) {
        loopData.battle = true
        overWorldListener.onBattleReady(enemy)
    }

    override fun onPlayerTurn() {
        loopData.enemyTurn = false
        overWorldListener.onPlayerTurn()
        allEnemies.rollDice()
    }

    override fun onEnemyTurn() {
        loopData.playerTurn = false
        loopData.enemyTurn = true
        overWorldListener.onEnemyTurn()
    }
}

private operator fun Float2.minusAssign(other: Float2) {
    this.x -= other.x
    this.y -= other.y
}
