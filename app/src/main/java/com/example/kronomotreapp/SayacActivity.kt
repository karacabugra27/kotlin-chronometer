package com.example.kronomotreapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kronomotreapp.databinding.ActivityMainBinding
import com.example.kronomotreapp.databinding.ActivitySayacBinding

class SayacActivity : AppCompatActivity() {
    lateinit var binding: ActivitySayacBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySayacBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}