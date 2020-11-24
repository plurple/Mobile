package com.example.neutrophil.menus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.neutrophil.R
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
        NewGame.setOnClickListener{ openNewGame() }
        LoadGame.setOnClickListener{ openLoadGame() }
        Settings.setOnClickListener{ openSettings() }
        Credits.setOnClickListener{ openCredits() }
        Close.setOnClickListener{ closeGame() }
    }

    private fun openNewGame() {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragmentContainer, NewGame())
        transaction.commit()
    }

    private fun openLoadGame() {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragmentContainer, SaveLoad())
        transaction.commit()
    }

    private fun openSettings() {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragmentContainer, Settings())
        transaction.commit()
    }

    private fun openCredits() {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragmentContainer, Credits())
        transaction.commit()
    }

    private fun closeGame() {
        exitProcess(0)
    }
}