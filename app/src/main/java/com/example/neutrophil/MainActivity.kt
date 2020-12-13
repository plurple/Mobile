package com.example.neutrophil

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.neutrophil.menus.MainMenu


class MainActivity : AppCompatActivity() {
    init {
        instance = this
    }

    companion object {
        private var instance: MainActivity? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("DEPRECATION")
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        SaveManager.addContext(applicationContext)
        setContentView(R.layout.activity_main)

        //open up the main menu fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, MainMenu())
        transaction.commit()


        SaveManager.loadSettings()
        SoundManager.playMusic(this, R.raw.lull)
    }
}