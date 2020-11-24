package com.example.neutrophil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.neutrophil.menus.MainMenu

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //open up the main menu fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, MainMenu())
        transaction.commit()
    }
}