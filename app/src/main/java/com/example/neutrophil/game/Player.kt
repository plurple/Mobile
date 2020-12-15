package com.example.neutrophil.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.renderscript.Float2
import android.renderscript.Int2
import com.example.neutrophil.R

class Player(@Transient private var context : Context){
    var tileOffset = Int2(0,0)
    var maxHealth = 100
    var health = maxHealth
    var numberSteps = 0
    var diceSides = 6
    var numDice = 1
    var position = Float2(0.0f, 0.0f)
    @Transient var abilities = mutableListOf<Ability>(Ability())
    var numAbilities = 1
    @Transient lateinit var image: Bitmap
    var directions : List<Boolean> = listOf(true, true, true, true)
    var kills = mutableListOf(0, 0, 0, 0, 0)
    var totalSteps = 0
    var numHealthPotions = 3

    init {
        position = Float2(TileGlobals.tileSize * (TileGlobals.numHorizontalTiles/2), TileGlobals.tileSize*(TileGlobals.numVerticalTiles/2))
        setUp(context)
        rollDice()
    }

    fun setUp(context: Context)
    {
        image = BitmapFactory.decodeResource(context.resources, R.drawable.player)
        abilities = mutableListOf<Ability>(Ability())
    }


    fun draw(canvas: Canvas) {
        canvas.drawBitmap(image, position.x, position.y,null )
    }

    fun rollDice(){
        if(numberSteps <= 0) {
            for (i in 1..numDice) {
                numberSteps += (1..diceSides).random()
            }
        }
    }

    fun checkForOverlap(enemies: MutableList<Enemy>) : Enemy? {
        for (enemy in enemies){
            if(position.x == enemy.position.x && position.y == enemy.position.y){
                return enemy
            }
        }
        return null
    }

    fun checkForOverlap(items: MutableList<Item>) : Item? {
        for (item in items){
            if(position.x == item.position.x && position.y == item.position.y){
                return item
            }
        }
        return null
    }

    fun moveUp(){
        if(numberSteps > 0) {
            position.y -= TileGlobals.tileSize
            tileOffset.y--
            numberSteps--
            totalSteps++
        }
    }

    fun moveDown(){
        if(numberSteps > 0) {
            position.y += TileGlobals.tileSize
            tileOffset.y++
            numberSteps--
            totalSteps++
        }
    }

    fun moveLeft(){
        if(numberSteps > 0) {
            position.x -= TileGlobals.tileSize
            tileOffset.x--
            numberSteps--
            totalSteps++
        }
    }

    fun moveRight(){
        if(numberSteps > 0) {
            position.x += TileGlobals.tileSize
            tileOffset.x++
            numberSteps--
            totalSteps++
        }
    }

    fun addAbility(type : Int){
        var ability = Ability()
        when(type){
            1 ->{
                ability.name = "fish"
                ability.type = EnemyVariety.Bacteria
            }
            2 ->{
                ability.name = "lightning"
                ability.type = EnemyVariety.Virus
            }
            3 ->{
                ability.name = "triangle"
                ability.type = EnemyVariety.Parasite
            }
            4 ->{
                ability.name = "swirl"
                ability.type = EnemyVariety.Fungus
            }
        }
        if(abilities.contains(ability)) return
        abilities.add(ability)
        numAbilities++
    }

    fun modifyHealth(damage : Int){
        health += damage
        if(health <= 0)
            health = 0
        if(health > maxHealth)
            health = maxHealth
    }

    fun consumeHealth(){
        if(numHealthPotions > 0){
            modifyHealth(50)
            numHealthPotions--
        }
    }
}