package com.example.neutrophil.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.neutrophil.R
import com.example.neutrophil.SaveManager
import com.example.neutrophil.menus.Credits
import com.example.neutrophil.menus.Settings
import kotlinx.android.synthetic.main.fragment_main_menu.*
import kotlin.system.exitProcess

class Death : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_death, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newGame.setOnClickListener{ startNewGame() }
        settings.setOnClickListener{ openSettings() }
        credits.setOnClickListener{ openCredits() }
        close.setOnClickListener{ closeGame() }
    }

    private fun openSettings() {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragmentContainer, Settings())
        transaction.addToBackStack("OpenSettings")
        transaction.commit()
    }

    private fun openCredits() {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragmentContainer, Credits())
        transaction.addToBackStack("OpenCredits")
        transaction.commit()
    }

    private fun closeGame() {
        exitProcess(0)
    }

    private fun startNewGame() {
        SaveManager.clearData()
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragmentContainer, OverWorld(context!!))
        transaction.commit()
    }
}