package com.example.neutrophil.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.renderscript.Float2
import com.example.neutrophil.R

enum class EnemyVariety{
    None,
    Bacteria,
    Virus,
    Parasite,
    Fungus
}

class Enemy(@Transient var context: Context, var enemyType : Int) {
    var name = "enemy"
    var maxHealth = 100
    var health = maxHealth
    var numberSteps = 0
    var diceSides = 6
    var numDice = 1
    var position = Float2(0.0f, 0.0f)
    var variety = EnemyVariety.Bacteria
    var damage = -5
    var onScreen = true
    @Transient lateinit var image: Bitmap
    var directions : List<Boolean> = listOf(true, true, true, true)


    init{
        setEnemy(context)
        rollDice()
        position = Float2(TileGlobals.tileSize * (0 until TileGlobals.numHorizontalTiles).random(), TileGlobals.tileSize * (0 until TileGlobals.numVerticalTiles).random())
    }

    fun draw(canvas: Canvas) {
        if(onScreen)
            canvas.drawBitmap(image, position.x, position.y,null )
    }

    fun rollDice(){
        if(numberSteps <= 0) {
            for (i in 1..numDice) {
                numberSteps += (1..diceSides).random()
            }
        }
    }

    fun update(){
        onScreen = (position.x >=0.0f && position.x <= TileGlobals.tileSize * TileGlobals.numHorizontalTiles &&
                position.y >= 0.0f && position.y <= TileGlobals.tileSize * TileGlobals.numVerticalTiles)
    }

    fun updateMove(){
        if(numberSteps == 0) return
        moveEnemy()
    }

    fun battleUpdate(player: Player){
        player.modifyHealth(damage)
    }

    fun setEnemy(context: Context) {
        when(enemyType) {
            -1 ->{
                image = BitmapFactory.decodeResource(context.resources, R.drawable.arrow_up)
            }
            0 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.red_bean)
                variety = EnemyVariety.Bacteria
                name = "Bacteria"
            }
            4 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.orange_blob)
                variety = EnemyVariety.Bacteria
                name = "Bacteria"
            }
            8 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.blue_flat)
                variety = EnemyVariety.Bacteria
                name = "Bacteria"
            }
            12 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.blue_wide_blob)
                variety = EnemyVariety.Bacteria
                name = "Bacteria"
            }
            16 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.pink_twins)
                variety = EnemyVariety.Bacteria
                name = "Bacteria"
            }
            20 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.green_split)
                variety = EnemyVariety.Bacteria
                name = "Bacteria"
            }
            2 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.green_blob)
                variety = EnemyVariety.Virus
                name = "Virus"
            }
            6 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.yellow_eye)
                variety = EnemyVariety.Virus
                name = "Virus"
            }
            10 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.yellow_blob)
                variety = EnemyVariety.Bacteria
                name = "virus"
            }
            14 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.blue_green_merged)
                variety = EnemyVariety.Virus
                name = "Virus"
            }
            18 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.purple_blob)
                variety = EnemyVariety.Virus
                name = "Virus"
            }
            22 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.pink_crystal)
                variety = EnemyVariety.Virus
                name = "Virus"
            }
            3 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.pink_worm)
                variety = EnemyVariety.Parasite
                name = "Parasite"
            }
            7 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.green_tadpol)
                variety = EnemyVariety.Parasite
                name = "Parasite"
            }
            11 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.orange_jellyfish)
                variety = EnemyVariety.Parasite
                name = "Parasite"
            }
            15 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.red_tadpol)
                variety = EnemyVariety.Parasite
                name = "Parasite"
            }
            19 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.red_worm)
                variety = EnemyVariety.Parasite
                name = "Parasite"
            }
            1 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.blue_cucumber)
                variety = EnemyVariety.Fungus
                name = "Fungus"
            }
            5 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.triplets)
                variety = EnemyVariety.Fungus
                name = "Fungus"
            }
            9 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.purple_handy)
                variety = EnemyVariety.Fungus
                name = "Fungus"
            }
            13 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.red_tall_blob)
                variety = EnemyVariety.Fungus
                name = "Fungus"
            }
            17 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.pink_blob)
                variety = EnemyVariety.Fungus
                name = "Fungus"
            }
            21 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.yellow_triplets)
                variety = EnemyVariety.Fungus
                name = "Fungus"
            }
        }
    }

    fun modifyHealth(damage : Int){
        health += damage
        if(health <= 0)
            health = 0
        if(health > maxHealth)
            health = maxHealth
    }

    private fun moveEnemy(){
        var pickDirection = true
        val possibleDirections = mutableListOf(0,1,2,3)
        var direction = (possibleDirections).random()

        while(pickDirection) {
            if(directions[direction])
                pickDirection = false
            else {
                possibleDirections.remove(direction)
                direction = (possibleDirections).random()
            }
        }
        when(direction){
            0 ->{
                position.y -= TileGlobals.tileSize
            }
            1 ->{
                position.x += TileGlobals.tileSize
            }
            2 ->{
                position.y += TileGlobals.tileSize
            }
            3 ->{
                position.x -= TileGlobals.tileSize
            }
        }
        numberSteps--
    }

}