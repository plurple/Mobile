package com.example.neutrophil.game

import android.graphics.Canvas
import android.view.SurfaceHolder

class BattleThread(private val surfaceHolder: SurfaceHolder, private val battleView: BattleView) : Thread() {
    private var running : Boolean = false

    fun setRunning(isRunning: Boolean){
        this.running = isRunning
    }

    override fun run() {
        while (running) {
            canvas = null
            try {
                // locking the canvas allows us to draw on to itKotlinNullPointerException
                canvas = this.surfaceHolder.lockCanvas()
                synchronized(surfaceHolder) {
                    this.battleView.update()
                    if (canvas != null)
                        this.battleView.draw(canvas!!)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    companion object{
        private var canvas: Canvas? = null
    }
}