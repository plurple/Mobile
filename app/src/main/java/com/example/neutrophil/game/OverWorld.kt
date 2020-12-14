package com.example.neutrophil.game

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.neutrophil.R
import com.example.neutrophil.SaveManager
import com.example.neutrophil.menus.Pause
import kotlinx.android.synthetic.main.fragment_over_world.*
import kotlin.math.abs

class OverWorld(context: Context) : Fragment(), SensorEventListener, OverWorldListener {
    private var sensorManager : SensorManager
    private var accelerometer : Sensor

    init {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME)
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
        diceRoll.setOnClickListener{ rollDice() }
        upArrow.setOnClickListener{ moveUp() }
        downArrow.setOnClickListener{ moveDown() }
        leftArrow.setOnClickListener{ moveLeft() }
        rightArrow.setOnClickListener{ moveRight() }
        gameView.gameLoop.overWorldLateInit(this)
        setUpUI()
    }

    private fun setUpUI() {
        healthBar.max = gameView.gameLoop.player.maxHealth
        healthBar.progress = gameView.gameLoop.player.health
        healthValue.text = healthBar.progress.toString() + "/" + healthBar.max.toString()
        numMoves.text = gameView.gameLoop.player.numberSteps.toString()
        if(gameView.gameLoop.player.directions[0]) upArrow.visibility = View.VISIBLE
        else upArrow.visibility = View.INVISIBLE
        if(gameView.gameLoop.player.directions[1]) rightArrow.visibility = View.VISIBLE
        else rightArrow.visibility = View.INVISIBLE
        if(gameView.gameLoop.player.directions[2]) downArrow.visibility = View.VISIBLE
        else downArrow.visibility = View.INVISIBLE
        if(gameView.gameLoop.player.directions[3]) leftArrow.visibility = View.VISIBLE
        else leftArrow.visibility = View.INVISIBLE
        if(gameView.gameLoop.player.numberSteps != 0) diceRoll.visibility = View.INVISIBLE
        else diceRoll.visibility = View.VISIBLE
    }

    private fun saveGameLoop() {
        SaveManager.savePlayer(gameView.gameLoop.player)
        SaveManager.saveEnemies(gameView.gameLoop.allEnemies)
        SaveManager.saveTiles(gameView.gameLoop.tileManager)
        SaveManager.saveLoopData(gameView.gameLoop.loopData)
    }

    private fun openPause() {
        saveGameLoop()
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragmentContainer, Pause())
        transaction.addToBackStack("OpenPauseMenu")
        transaction.commit()
    }

    private fun openInventory() {
        saveGameLoop()
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragmentContainer, Inventory())
        transaction.addToBackStack("OpenInventory")
        transaction.commit()
    }

    private fun rollDice() {
        gameView.gameLoop.player.rollDice()
        gameView.gameLoop.loopData.playerTurn = true
        setUpUI()
        diceRoll.visibility = View.INVISIBLE
        sensorManager.unregisterListener(this)
    }

    private fun moveUp() {
        gameView.gameLoop.player.moveUp()
        gameView.gameLoop.player.directions = gameView.gameLoop.tileManager.getTileDirections(gameView.gameLoop.player.position)
        setUpUI()
    }

    private fun moveDown() {
        gameView.gameLoop.player.moveDown()
        gameView.gameLoop.player.directions = gameView.gameLoop.tileManager.getTileDirections(gameView.gameLoop.player.position)
        setUpUI()
    }

    private fun moveLeft() {
        gameView.gameLoop.player.moveLeft()
        gameView.gameLoop.player.directions = gameView.gameLoop.tileManager.getTileDirections(gameView.gameLoop.player.position)
        setUpUI()
    }

    private fun moveRight() {
        gameView.gameLoop.player.moveRight()
        gameView.gameLoop.player.directions = gameView.gameLoop.tileManager.getTileDirections(gameView.gameLoop.player.position)
        setUpUI()
    }

    override fun onBattleReady(enemy: Enemy) {
        saveGameLoop()
        SaveManager.saveEnemy(enemy)
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragmentContainer, Battle())
        transaction.addToBackStack("OpenBattle")
        transaction.commit()
    }

    override fun onPlayerTurn(){
        Handler(Looper.getMainLooper()).postDelayed({
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME)
            diceRoll.visibility = View.VISIBLE
            upArrow.visibility = View.VISIBLE
            downArrow.visibility = View.VISIBLE
            leftArrow.visibility = View.VISIBLE
            rightArrow.visibility = View.VISIBLE
            inventoryButton.visibility = View.VISIBLE}, 0)
    }

    override fun onEnemyTurn(){
        Handler(Looper.getMainLooper()).postDelayed({
            sensorManager.unregisterListener(this)
            diceRoll.visibility = View.INVISIBLE
            upArrow.visibility = View.INVISIBLE
            downArrow.visibility = View.INVISIBLE
            leftArrow.visibility = View.INVISIBLE
            rightArrow.visibility = View.INVISIBLE
            inventoryButton.visibility = View.INVISIBLE}, 0)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) { }

    override fun onSensorChanged(event: SensorEvent?) {
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