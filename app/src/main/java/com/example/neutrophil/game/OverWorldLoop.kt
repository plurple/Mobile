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
    var allItems = ItemManager()
    var loopData = LoopData()
    var levelManager = LevelManager()
    private lateinit var overWorldListener : OverWorldListener

    fun overWorldLateInit(owListener: OverWorldListener) {
        levelManager = SaveManager.loadLevelManager()
        if(levelManager.newGame) loadLevel()
        else {
            tileManager = SaveManager.loadTiles()
            allEnemies = SaveManager.loadEnemies()
            allItems = SaveManager.loadItems()
            player = SaveManager.loadPlayer()
            loopData = SaveManager.loadLoopData()

            player.directions = tileManager.getTileDirections(player.position)
            for(enemy in allEnemies.enemies) enemy.directions = tileManager.getTileDirections(enemy.position)
        }
        tileManager.setup(context)
        allEnemies.setup(context)
        allItems.setup(context)
        player.setUp(context)

        loopData.battle = false
        levelManager.newGame = false

        overWorldListener = owListener
    }

    fun update() {
        if(!loopData.battle) {
            if(player.position.x < 0.0f || player.position.x >= TileGlobals.tileSize * TileGlobals.numHorizontalTiles ||
                player.position.y < 0.0f || player.position.y >= TileGlobals.tileSize * TileGlobals.numVerticalTiles)
                recenterPlayer()
            if (player.numberSteps == 0 && loopData.playerTurn) {
                onEnemyTurn()
            }
            allItems.update()
            tileManager.update()
            allEnemies.update()
            if (loopData.enemyTurn) {
                Thread.sleep(250)
                if (allEnemies.updateMove(tileManager)) {
                    onPlayerTurn()
                }
            }
            val item = player.checkForOverlap(allItems.items)
            if (item != null) {
                allItems.items.remove(item)
                allItems.numItems--
                item.pickUp(player)
                onSetUpUI()
            }
            val enemy = player.checkForOverlap(allEnemies.enemies)
            if (enemy != null) {
                allEnemies.enemies.remove(enemy)
                allEnemies.numEnemies--
                onBattleReady(enemy)
            }

            if (player.position.x == levelManager.nextLevelPos.x &&
                player.position.y == levelManager.nextLevelPos.y) {
                clearLevel()
                loadLevel()
                onSetUpUI()
            }
        }
    }

    fun draw(canvas: Canvas) {
        tileManager.draw(canvas)
        player.draw(canvas)
        allEnemies.draw(canvas)
        allItems.draw(canvas)
        levelManager.draw(context, canvas)
    }

    private fun recenterPlayer(){
        val adjustment = Float2(player.tileOffset.x * TileGlobals.tileSize, player.tileOffset.y * TileGlobals.tileSize)
        player.position -= adjustment
        tileManager.position -= adjustment
        levelManager.nextLevelPos -= adjustment
        for(tile in tileManager.tiles) tile.position -= adjustment
        for(enemy in allEnemies.enemies) enemy.position -= adjustment
        for(item in allItems.items) item.position -= adjustment
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

    override fun onSetUpUI(){
        overWorldListener.onSetUpUI()
    }

    private fun clearLevel(){
        allItems.items.clear()
        allItems.numItems = 0
        allEnemies.enemies.clear()
        allEnemies.numEnemies = 0
        tileManager.tiles.clear()
        tileManager.numTiles = 0
    }

    private fun loadLevel(){
        levelManager.currentLevel = levelManager.nextLevel
        val level = Levels.levels[levelManager.currentLevel]
        for(i in 0 until level.numCellsY){
            for(j in 0 until level.numCellsX){
                if(level.listOfTiles[i*10+j] == -1) continue
                val tile = Tile(context, level.listOfTiles[i*10+j])
                tile.position = Float2(j*TileGlobals.tileSize, i*TileGlobals.tileSize)
                tileManager.tiles.add(tile)
                tileManager.numTiles++
            }
        }
        player.position = Float2(level.playerPos.x*TileGlobals.tileSize,level.playerPos.y*TileGlobals.tileSize)
        player.directions = tileManager.getTileDirections(player.position)
        player.tileOffset = Int2(level.playerPos.x - (TileGlobals.numHorizontalTiles/2), level.playerPos.y - (TileGlobals.numVerticalTiles/2))
        for(potion in level.listOfHealthPotions) {
            val item = Item(context)
            item.position = Float2(potion.x*TileGlobals.tileSize,potion.y*TileGlobals.tileSize)
            allItems.items.add(item)
            allItems.numItems++
        }
        for(levelEnemy in level.listOfEnemies){
            val enemy = Enemy(context, levelEnemy.type)
            enemy.position = Float2(levelEnemy.position.x*TileGlobals.tileSize, levelEnemy.position.y*TileGlobals.tileSize)
            enemy.directions = tileManager.getTileDirections(enemy.position)
            allEnemies.enemies.add(enemy)
            allEnemies.numEnemies++
        }

        levelManager.nextLevel = level.nextLevel
        levelManager.nextLevelPos = Float2(level.nextLevelPos.x*TileGlobals.tileSize, level.nextLevelPos.y*TileGlobals.tileSize)
        recenterPlayer()
    }
}

private operator fun Float2.minusAssign(other: Float2) {
    this.x -= other.x
    this.y -= other.y
}
