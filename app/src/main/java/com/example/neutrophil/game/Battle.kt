package com.example.neutrophil.game

import android.gesture.Gesture
import android.gesture.GestureLibraries
import android.gesture.GestureLibrary
import android.gesture.GestureOverlayView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.neutrophil.R
import com.example.neutrophil.SaveManager
import kotlinx.android.synthetic.main.fragment_battle.*

class Battle() : Fragment(), GestureOverlayView.OnGesturePerformedListener {
    lateinit var player : Player
    lateinit var enemy : Enemy
    private var gLibrary: GestureLibrary? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gLibrary = GestureLibraries.fromRawResource(context,
            R.raw.gesture)
        if (gLibrary?.load() == false) {
            fragmentManager!!.popBackStack()
        }
    }

    //TODO("get the battle stuff working recognising the gestures that are unlocked and stuff")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_battle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        player = SaveManager.loadPlayer()
        enemy = SaveManager.loadEnemy()
        setUI()
        retreatButton.setOnClickListener { retreat() }
        atkInput.addOnGesturePerformedListener(this)
    }

    private fun retreat() {
        SaveManager.savePlayer(player)
        fragmentManager!!.popBackStack()
    }

    private fun setUI()
    {
        playerHealth.max = player.maxHealth
        playerHealth.progress = player.health
        playerHealthValues.text = playerHealth.progress.toString() + "/" + playerHealth.max.toString()
        enemyName.text = enemy.name
        enemyHealth.max = enemy.maxHealth
        enemyHealth.progress = enemy.health
        enemyHealthValues.text = enemyHealth.progress.toString() + "/" + enemyHealth.max.toString()
    }

    override fun onGesturePerformed(overlay: GestureOverlayView?, gesture: Gesture?) {
        val predictions = gLibrary?.recognize(gesture)

        predictions?.let {
            if (it.size > 0 && it[0].score > 1.0) {
                val action = it[0].name
                Toast.makeText(context, action, Toast.LENGTH_SHORT).show()
            }
        }
    }
}