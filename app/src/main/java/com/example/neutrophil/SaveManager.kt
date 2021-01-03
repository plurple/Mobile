package com.example.neutrophil

import android.content.Context
import android.content.SharedPreferences
import com.example.neutrophil.game.*
import com.google.gson.Gson

object SaveManager{
    private lateinit var context :Context
    private lateinit var sharedPref :SharedPreferences

    fun addContext(applicationContext: Context) {
        context = applicationContext
        sharedPref = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
    }

    fun savePlayer(player : Player) {
        with (sharedPref.edit()) {
            putString(context.getString(R.string.savedPlayer), Gson().toJson(player))
            for((count, ability) in player.abilities.withIndex()){
                putString(context.getString(R.string.savedAbility) + count, Gson().toJson(ability))
            }
            apply()
        }
    }

    fun loadPlayer() : Player {
        val savedPlayer = sharedPref.getString(context.getString(R.string.savedPlayer), "")
        if(savedPlayer!!.isEmpty()) return Player(context)
        val player =  Gson().fromJson(savedPlayer, Player::class.java)
        player.setUp(context)
        for(i in 0 until player.numAbilities)
        {
            val savedAbility = sharedPref.getString(context.getString(R.string.savedAbility)+i, "")
            val ability = Gson().fromJson(savedAbility, Ability::class.java)
            player.abilities.add(ability)
        }
        return player
    }

    fun saveEnemy(enemy: Enemy){
        with (sharedPref.edit()) {
            putString(context.getString(R.string.savedEnemy), Gson().toJson(enemy))
            apply()
        }
    }

    fun loadEnemy() : Enemy {
        val savedEnemy = sharedPref.getString(context.getString(R.string.savedEnemy), "")
        if(savedEnemy!!.isEmpty()) return Enemy(context, -1)
        val enemy = Gson().fromJson(savedEnemy, Enemy::class.java)
        enemy.setEnemy(context)
        return enemy
    }

    fun saveEnemies(enemyManager: EnemyManager){
        with (sharedPref.edit()) {
            putString(context.getString(R.string.savedEnemies), Gson().toJson(enemyManager))
            for((count, enemy) in enemyManager.enemies.withIndex()){
                putString(context.getString(R.string.savedEnemy) + count, Gson().toJson(enemy))
            }
            apply()
        }
    }

    fun loadEnemies() : EnemyManager {
        val savedEnemies = sharedPref.getString(context.getString(R.string.savedEnemies), "")
        if(savedEnemies!!.isEmpty()) return EnemyManager()
        val enemyManager = Gson().fromJson(savedEnemies, EnemyManager::class.java)
        for(i in 0 until enemyManager.numEnemies)
        {
            val savedEnemy = sharedPref.getString(context.getString(R.string.savedEnemy)+i, "")
            val enemy = Gson().fromJson(savedEnemy, Enemy::class.java)
            enemyManager.enemies.add(enemy)
        }
        return enemyManager
    }

    fun saveTiles(tileManager: TileManager){
        with (sharedPref.edit()) {
            putString(context.getString(R.string.savedTiles), Gson().toJson(tileManager))
            for((count, tile) in tileManager.tiles.withIndex()){
                putString(context.getString(R.string.savedTile) + count, Gson().toJson(tile))
            }
            apply()
        }
    }

    fun loadTiles() : TileManager{
        val savedTiles = sharedPref.getString(context.getString(R.string.savedTiles), "")
        if(savedTiles!!.isEmpty()) return TileManager()
        val tileManager = Gson().fromJson(savedTiles, TileManager::class.java)
        for(i in 0 until tileManager.numTiles)
        {
            val savedTile = sharedPref.getString(context.getString(R.string.savedTile)+i, "")
            val tile = Gson().fromJson(savedTile, Tile::class.java)
            tileManager.tiles.add(tile)
        }
        return tileManager
    }

    fun saveItems(itemManager: ItemManager){
        with (sharedPref.edit()) {
            putString(context.getString(R.string.savedItems), Gson().toJson(itemManager))
            for((count, item) in itemManager.items.withIndex()){
                putString(context.getString(R.string.savedItem) + count, Gson().toJson(item))
            }
            apply()
        }
    }

    fun loadItems() : ItemManager{
        val savedItems = sharedPref.getString(context.getString(R.string.savedItems), "")
        if(savedItems!!.isEmpty()) return ItemManager()
        val itemManager = Gson().fromJson(savedItems, ItemManager::class.java)
        for(i in 0 until itemManager.numItems)
        {
            val savedItem = sharedPref.getString(context.getString(R.string.savedItem)+i, "")
            val item = Gson().fromJson(savedItem, Item::class.java)
            itemManager.items.add(item)
        }
        return itemManager
    }

    fun saveLoopData(loopData: LoopData){
        with (sharedPref.edit()) {
            putString(context.getString(R.string.savedLoop), Gson().toJson(loopData))
            apply()
        }
    }

    fun loadLoopData(): LoopData{
        val savedLoop = sharedPref.getString(context.getString(R.string.savedLoop), "")
        if(savedLoop!!.isEmpty()) return LoopData()
        return Gson().fromJson(savedLoop, LoopData::class.java)
    }

    fun saveSettings()
    {
        with (sharedPref.edit()) {
            putFloat(context.getString(R.string.savedMusic), SoundManager.musicLevel)
            putFloat(context.getString(R.string.savedSounds), SoundManager.soundsLevel)
            apply()
        }
    }

    fun loadSettings()
    {
        SoundManager.musicLevel = sharedPref.getFloat(context.getString(R.string.savedMusic), 0.5f)
        SoundManager.soundsLevel = sharedPref.getFloat(context.getString(R.string.savedSounds), 0.5f)
    }

    fun saveLevelManager(levelManager: LevelManager){
        with (sharedPref.edit()) {
            putString(context.getString(R.string.savedLevelManager), Gson().toJson(levelManager))
            apply()
        }
    }

    fun loadLevelManager(): LevelManager{
        val savedLevelManager = sharedPref.getString(context.getString(R.string.savedLevelManager), "")
        if(savedLevelManager!!.isEmpty()) return LevelManager()
        return Gson().fromJson(savedLevelManager, LevelManager::class.java)
    }

    fun clearData() {
        with(sharedPref.edit()) {
            putString(context.getString(R.string.savedPlayer), "")
            putString(context.getString(R.string.savedLoop), "")
            putString(context.getString(R.string.savedLevelManager), "")
            putString(context.getString(R.string.savedEnemies), "")
            for(i in 0..100){
                putString(context.getString(R.string.savedEnemy)+i, "")
            }
            putString(context.getString(R.string.savedItems), "")
            for(i in 0..100){
                putString(context.getString(R.string.savedItem)+i, "")
            }
            putString(context.getString(R.string.savedTiles), "")
            for(i in 0..100){
                putString(context.getString(R.string.savedTile)+i, "")
            }
            apply()
        }
    }
}