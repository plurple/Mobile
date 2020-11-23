package com.example.neutrophil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.neutrophil.menus.MainMenu
import com.example.dungeonexplorer.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newFragment = MainMenu()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, newFragment)
        transaction.commit()
    }
}