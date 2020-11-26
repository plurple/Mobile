package com.example.neutrophil.menus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.neutrophil.R
import com.example.neutrophil.game.OverWorld
import kotlinx.android.synthetic.main.fragment_main_menu.*
import kotlin.system.exitProcess

class MainMenu : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newGame.setOnClickListener{ openNewGame() }
        loadGame.setOnClickListener{ openLoadGame() }
        settings.setOnClickListener{ openSettings() }
        credits.setOnClickListener{ openCredits() }
        close.setOnClickListener{ closeGame() }
        OkButton.setOnClickListener { startNewGame() }
        cancelButton.setOnClickListener { closePopup() }
    }

    private fun openNewGame() {
        popup.visibility = View.VISIBLE
    }

    private fun openLoadGame() {
        if(popup.visibility == View.INVISIBLE) {
            val transaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.fragmentContainer, SaveLoad())
            transaction.addToBackStack("openLoadGame")
            transaction.commit()
        }
    }

    private fun openSettings() {
        if(popup.visibility == View.INVISIBLE) {
            val transaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.fragmentContainer, Settings())
            transaction.addToBackStack("OpenSettings")
            transaction.commit()
        }
    }

    private fun openCredits() {
        if(popup.visibility == View.INVISIBLE) {
            val transaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.fragmentContainer, Credits())
            transaction.addToBackStack("OpenCredits")
            transaction.commit()
        }
    }

    private fun closeGame() {
        if(popup.visibility == View.INVISIBLE) {
            exitProcess(0)
        }
    }

    private fun startNewGame() {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragmentContainer, OverWorld())
        transaction.commit()
    }

    private fun closePopup() {
        popup.visibility = View.INVISIBLE
    }
}