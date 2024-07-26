package com.example.kronomotreapp

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kronomotreapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var timePause: Long = 0

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
}