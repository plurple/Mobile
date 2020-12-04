package com.example.neutrophil.game

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class OverWorldView(context : Context, attributes: AttributeSet) : SurfaceView(context), SurfaceHolder.Callback {
    private lateinit var thread : OverWorldThread
    val gameLoop : OverWorldLoop = OverWorldLoop(context)

    init {
        holder.addCallback(this)
        newOverWorldThread()
    }

    private fun newOverWorldThread() {
        thread = OverWorldThread(holder, this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        thread.setRunning(true)
        thread.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        while(retry){
            tryWaitingForThreadToDie()
            retry = false
        }
    }

    private fun tryWaitingForThreadToDie() {
        try {
            thread.setRunning(false)
            thread.join()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun update(){
        gameLoop.update()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        gameLoop.draw(canvas)
    }

    fun pause() {
        gameLoop.pause()
    }

    fun resume(){
        newOverWorldThread()
        gameLoop.resume()
    }
}