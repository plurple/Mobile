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
    }

    private fun openPause() {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragmentContainer, Pause())
        transaction.addToBackStack("OpenPauseMenu")
        transaction.commit()
    }

}