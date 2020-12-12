package com.example.neutrophil.game

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.renderscript.Float2
import com.example.neutrophil.R

enum class EnemyVariety{
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
    @Transient lateinit var image: Bitmap
    var overWorldAI = EnemyOWAI()

    init{
        setEnemy(context)
        rollDice()
        position = Float2(TileGlobals.tileSize * (0 until TileGlobals.numHorizontalTiles).random(), TileGlobals.tileSize * (0 until TileGlobals.numVerticalTiles).random())
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

    fun update(){
        if(numberSteps == 0) return
        overWorldAI.moveEnemy(this)
        numberSteps--
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
                image = BitmapFactory.decodeResource(context.resources, R.drawable.bean)
                variety = EnemyVariety.Bacteria
            }
            1 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.cucumber)
                variety = EnemyVariety.Bacteria
            }
            2 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.green_tadpol)
                variety = EnemyVariety.Virus
            }
            3 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.handy)
                variety = EnemyVariety.Fungus
            }
            4 -> {
                image = BitmapFactory.decodeResource(context.resources, R.drawable.jellyfish)
                variety = EnemyVariety.Parasite
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

}