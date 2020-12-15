package com.example.neutrophil.menus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.example.neutrophil.R
import com.example.neutrophil.SaveManager
import com.example.neutrophil.SoundManager
import kotlinx.android.synthetic.main.fragment_settings.*

class Settings () : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        close.setOnClickListener { closeSettings() }
        setUpSoundsSlider()
        setUpMusicSlider()
    }

    private fun closeSettings() {
        SaveManager.saveSettings()
        fragmentManager!!.popBackStack()
    }

    private fun setUpMusicSlider() {
        musicSlider.progress = (SoundManager.musicLevel * 100).toInt()
        musicNumber.text = musicSlider.progress.toString()
        musicSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                musicNumber.text = musicSlider.progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                SoundManager.musicLevel = seekBar.progress / 100f
            }
        })
    }

    private fun setUpSoundsSlider() {
        soundsSlider.progress = (SoundManager.soundsLevel * 100).toInt()
        soundNumber.text = soundsSlider.progress.toString()
        soundsSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                soundNumber.text = soundsSlider.progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                SoundManager.soundsLevel = seekBar.progress / 100f
            }
        })
    }
}