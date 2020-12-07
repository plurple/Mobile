package com.example.neutrophil.game

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.neutrophil.R
import com.example.neutrophil.menus.Pause
import kotlinx.android.synthetic.main.fragment_over_world.*
import kotlin.math.abs

class OverWorld(context: Context) : Fragment(), SensorEventListener {
    private var sensorManager : SensorManager
    private var accelerometer : Sensor

    init{
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        sensorManager!!.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_over_world, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pauseButton.setOnClickListener{ openPause() }
        inventoryButton.setOnClickListener{ openInventory() }
        mapButton.setOnClickListener{ openMap() }
        diceRoll.setOnClickListener{ rollDice() }
        upArrow.setOnClickListener{ moveUp() }
        downArrow.setOnClickListener{ moveDown() }
        leftArrow.setOnClickListener{ moveLeft() }
        rightArrow.setOnClickListener{ moveRight() }
        setUpText()
    }

    fun setUpText()
    {
        healthBar.max = gameView.gameLoop.player.maxHealth
        healthBar.progress = gameView.gameLoop.player.health
        healthValue.text = healthBar.progress.toString() + "/" + healthBar.max.toString()
        numMoves.text = gameView.gameLoop.player.numberSteps.toString()
    }

    private fun openPause() {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragmentContainer, Pause())
        transaction.addToBackStack("OpenPauseMenu")
        transaction.commit()
    }

    private fun openInventory() {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragmentContainer, Inventory())
        transaction.addToBackStack("OpenInventory")
        transaction.commit()
    }

    private fun openMap() {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragmentContainer, Map())
        transaction.addToBackStack("OpenMap")
        transaction.commit()
    }

    private fun rollDice() {
        gameView.gameLoop.player.rollDice()
        setUpText()
    }

    private fun moveUp() {
        gameView.gameLoop.player.moveUp()
        setUpText()
    }

    private fun moveDown() {
        gameView.gameLoop.player.moveDown()
        setUpText()
    }

    private fun moveLeft() {
        gameView.gameLoop.player.moveLeft()
        setUpText()
    }

    private fun moveRight() {
        gameView.gameLoop.player.moveRight()
        setUpText()
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) { }

    override fun onSensorChanged(event: SensorEvent?){
        if (event != null) {
            val x = abs(event.values[0]/ SensorManager.GRAVITY_EARTH)
            val y = abs(event.values[1]/ SensorManager.GRAVITY_EARTH)
            val z = abs(event.values[2]/ SensorManager.GRAVITY_EARTH)

            if (x+y+z > 5.0f) {
                rollDice()
            }
        }
    }

}