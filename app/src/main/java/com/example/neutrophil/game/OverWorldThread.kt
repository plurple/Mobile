package com.example.neutrophil.game

import android.graphics.Canvas
import android.view.SurfaceHolder

class OverWorldThread(private val surfaceHolder: SurfaceHolder, private val overWorldView: OverWorldView) : Thread() {
    private var running : Boolean = false

    fun setRunning(isRunning: Boolean){
        this.running = isRunning
    }

    override fun run() {
        while(running){
            canvas = null
            updateFrame()
        }
    }

    private fun updateFrame() {
        try {
            updateOverWorldView()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            unlockCanvas()
        }
    }

    private fun updateOverWorldView() {
        canvas = this.surfaceHolder.lockCanvas()
        synchronized(surfaceHolder) {
            this.overWorldView.update()
            this.overWorldView.draw(canvas!!)
        }
    }

    private fun unlockCanvas() {
        if (canvas == null) return
        try {
            surfaceHolder.unlockCanvasAndPost(canvas)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object{
        private var canvas: Canvas? = null
    }
}