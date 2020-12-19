package com.example.neutrophil.game

import android.gesture.Gesture
import android.gesture.GestureLibraries
import android.gesture.GestureLibrary
import android.gesture.GestureOverlayView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.neutrophil.R
import com.example.neutrophil.SaveManager
import kotlinx.android.synthetic.main.fragment_battle.*


class Battle() : Fragment(), GestureOverlayView.OnGesturePerformedListener {
    private var gLibrary: GestureLibrary? = null
    lateinit var player: Player
    lateinit var battleEnemy : Enemy
    var playerTurn = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gLibrary = GestureLibraries.fromRawResource(context,
            R.raw.gesture)
        if (gLibrary?.load() == false) {
            fragmentManager!!.popBackStack()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_battle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        player = SaveManager.loadPlayer()
        playerImage.setImageBitmap(player.image)
        battleEnemy = SaveManager.loadEnemy()
        enemyImage.setImageBitmap(battleEnemy.image)
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
        setPlayerUI()
        setEnemyUI()
    }

    fun setPlayerUI(){
        playerHealth.max = player.maxHealth
        playerHealth.progress = player.health
        playerHealthValues.text = playerHealth.progress.toString() + "/" + playerHealth.max.toString()
    }

    fun setEnemyUI(){
        enemyName.text = battleEnemy.name
        enemyHealth.max = battleEnemy.maxHealth
        enemyHealth.progress = battleEnemy.health
        enemyHealthValues.text = enemyHealth.progress.toString() + "/" + enemyHealth.max.toString()
    }

    override fun onGesturePerformed(overlay: GestureOverlayView?, gesture: Gesture?) {
        val predictions = gLibrary?.recognize(gesture)

        predictions?.let {
            if (it.size > 0 && it[0].score > 1.0) {
                val action = it[0].name
                for(ability in player.abilities)
                {
                    if(ability.name == action && playerTurn) {
                        if(ability.type == battleEnemy.variety)
                            battleEnemy.modifyHealth(ability.damage*2)
                        else
                            battleEnemy.modifyHealth(ability.damage)
                    }
                    setEnemyUI()
                }
            }
        }
        playerTurn = false
        onEnemyTurn()
        playerTurn = true
    }

    fun battleOver() {
        player.kills[EnemyVariety.None.ordinal]++
        player.kills[battleEnemy.variety.ordinal]++
        if(player.kills[battleEnemy.variety.ordinal] == 5) player.addAbility(battleEnemy.variety.ordinal)
        SaveManager.savePlayer(player)
        fragmentManager!!.popBackStack()
    }
    fun onDeath(){
        SaveManager.savePlayer(player)
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragmentContainer, Death())
        transaction.commit()
    }

    fun onEnemyTurn() {
        if(!playerTurn) {
            if (battleEnemy.health != 0) {
                battleEnemy.battleUpdate(player)
                if (player.health == 0) {
                    onDeath()
                }
                setPlayerUI()
            } else {
                battleOver()
            }
        }
    }
}