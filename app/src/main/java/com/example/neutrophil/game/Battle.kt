package com.example.neutrophil.game

import android.gesture.Gesture
import android.gesture.GestureLibraries
import android.gesture.GestureLibrary
import android.gesture.GestureOverlayView
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.neutrophil.R
import com.example.neutrophil.SaveManager
import kotlinx.android.synthetic.main.fragment_battle.*


class Battle() : Fragment(), GestureOverlayView.OnGesturePerformedListener, BattleListener {
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
        battleView.battleLoop.battleLateInit(this)
        setUI()
        retreatButton.setOnClickListener { retreat() }
        atkInput.addOnGesturePerformedListener(this)
    }

    private fun retreat() {
        SaveManager.savePlayer(battleView.battleLoop.player)
        fragmentManager!!.popBackStack()
    }

    private fun setUI()
    {
        Handler(Looper.getMainLooper()).postDelayed({
        playerHealth.max = battleView.battleLoop.player.maxHealth
        playerHealth.progress = battleView.battleLoop.player.health
        playerHealthValues.text = playerHealth.progress.toString() + "/" + playerHealth.max.toString()
        enemyName.text = battleView.battleLoop.battleEnemy.name
        enemyHealth.max = battleView.battleLoop.battleEnemy.maxHealth
        enemyHealth.progress = battleView.battleLoop.battleEnemy.health
        enemyHealthValues.text = enemyHealth.progress.toString() + "/" + enemyHealth.max.toString()},0)
    }

    override fun onGesturePerformed(overlay: GestureOverlayView?, gesture: Gesture?) {
        val predictions = gLibrary?.recognize(gesture)

        predictions?.let {
            if (it.size > 0 && it[0].score > 1.0) {
                val action = it[0].name
                for(ability in battleView.battleLoop.player.abilities)
                {
                    if(ability.name == action) {
                        if(ability.type == battleView.battleLoop.battleEnemy.variety)
                            battleView.battleLoop.battleEnemy.modifyHealth(ability.damage*2)
                        else
                            battleView.battleLoop.battleEnemy.modifyHealth(ability.damage)
                    }
                    battleView.battleLoop.loopData.enemyTurn = true
                    battleView.battleLoop.loopData.playerTurn = false
                    setUI()
                }
            }
        }
    }

    override fun battleOver() {
        SaveManager.savePlayer(battleView.battleLoop.player)
        fragmentManager!!.popBackStack()
    }
    override fun onDeath(){
        Handler(Looper.getMainLooper()).postDelayed({
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragmentContainer, Death())
        transaction.commit()},0)
    }

    override fun onEnemyTurnEnd() {
        setUI()
    }
}