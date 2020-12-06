package com.example.neutrophil.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.neutrophil.R
import com.example.neutrophil.menus.Pause
import kotlinx.android.synthetic.main.fragment_over_world.*

class OverWorld : Fragment() {
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
        //TODO roll the dice here will also be able to shake the device to roll.
    }

    private fun moveUp() {
        gameView.gameLoop.player.moveUp()
    }

    private fun moveDown() {
        gameView.gameLoop.player.moveDown()
    }

    private fun moveLeft() {
        gameView.gameLoop.player.moveLeft()
    }

    private fun moveRight() {
        gameView.gameLoop.player.moveRight()
    }

}