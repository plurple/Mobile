package com.example.neutrophil.menus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.neutrophil.R
import com.example.neutrophil.game.OverWorld
import kotlinx.android.synthetic.main.fragment_pause.*

class Pause : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pause, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settings.setOnClickListener{ openSettings() }
        resume.setOnClickListener { closePause() }
        saveGame.setOnClickListener { saveGame() }
    }


    private fun openSettings() {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragmentContainer, Settings())
        transaction.addToBackStack("OpenSettings")
        transaction.commit()
    }

    private fun closePause() {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragmentContainer, OverWorld())
        transaction.commit()
    }

    private fun saveGame() {
        //TODO: set up the saving of the game here.
    }
}