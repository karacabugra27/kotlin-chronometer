package com.example.kronomotreapp

import android.content.Intent
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

        binding.kronometreButton.setOnClickListener {
            val intent = Intent(this, ChronometerActivity::class.java)
            startActivity(intent)
        }

        binding.countdownButton.setOnClickListener {
            val intent = Intent(this, SayacActivity::class.java)
            startActivity(intent)
        }

    }

}