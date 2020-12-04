package com.example.neutrophil.game

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.renderscript.Float2
import com.example.neutrophil.MainActivity
import com.example.neutrophil.R


class Player {
    private val context = MainActivity.mainActivityContext()
    var maxHealth = 100;
    var health = maxHealth;
    var numberSteps = 6;
    var diceSides = 6;
    var numDice = 1;
    var position = Float2(0.0f, 0.0f)
    var image: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.player)


    fun setup() {

    }

    fun draw(canvas: Canvas) {
        canvas.drawBitmap(image, position.x, position.y,null )
    }
}