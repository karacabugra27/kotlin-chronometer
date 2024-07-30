package com.example.kronomotreapp

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kronomotreapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var timePause: Long = 0
    private var isRunning: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            timePause = savedInstanceState.getLong("timePause")
            isRunning = savedInstanceState.getBoolean("isRunning")
            binding.kronometre.base = savedInstanceState.getLong("kronometreBase")
            //daha önce kaydedilen veriyi geri yüklenmesini sağlıyor.

            if (isRunning) {
                binding.kronometre.start()
                binding.startButton.visibility = View.GONE
                binding.pauseButton.visibility = View.VISIBLE
                binding.start.setImageDrawable(getDrawable(R.drawable.pause))
            } else {
                binding.kronometre.stop()
                binding.startButton.visibility = View.VISIBLE
                binding.pauseButton.visibility = View.GONE
                binding.start.setImageDrawable(getDrawable(R.drawable.start))
            }
        }

        binding.startButton.setOnClickListener {
            binding.kronometre.base = SystemClock.elapsedRealtime() + timePause
            binding.kronometre.start()
            binding.startButton.visibility = View.GONE
            binding.pauseButton.visibility = View.VISIBLE
            binding.start.setImageDrawable(getDrawable(R.drawable.pause))
        }

        binding.pauseButton.setOnClickListener {
            timePause = (binding.kronometre.base) - (SystemClock.elapsedRealtime())
            binding.kronometre.stop()
            binding.pauseButton.visibility = View.GONE
            binding.startButton.visibility = View.VISIBLE
            binding.start.setImageDrawable(getDrawable(R.drawable.start))
        }

        binding.resetButton.setOnClickListener {
            binding.kronometre.base = SystemClock.elapsedRealtime()
            timePause = 0
            binding.kronometre.stop()
            binding.pauseButton.visibility = View.GONE
            binding.startButton.visibility = View.VISIBLE
            binding.start.setImageDrawable(getDrawable(R.drawable.start))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        //verinin kaybolmaması için kaydeden kısım
        super.onSaveInstanceState(outState)
        outState.putLong("timePause", timePause)
        outState.putBoolean("isRunning", isRunning)
        outState.putLong("kronometreBase", binding.kronometre.base)
        //kaydedilecek diğer ifadeler ve durumlar buraya yazılmalı.
    }


}