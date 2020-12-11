package com.example.neutrophil.game

import android.graphics.Canvas
import android.view.SurfaceHolder

class OverWorldThread(private val surfaceHolder: SurfaceHolder, private val overWorldView: OverWorldView) : Thread() {
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
                    this.overWorldView.update()
                    this.overWorldView.draw(canvas!!)
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