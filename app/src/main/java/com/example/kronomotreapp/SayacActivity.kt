package com.example.kronomotreapp

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.kronomotreapp.databinding.ActivitySayacBinding

class SayacActivity : AppCompatActivity() {
    lateinit var binding: ActivitySayacBinding
    var countDownTimer: CountDownTimer? = null
    var isRunning = false
    var timeLeft: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySayacBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {

            binding.saat.visibility = View.GONE
            binding.dakika.visibility = View.GONE
            binding.startButton.visibility = View.GONE
            binding.pauseButton.visibility = View.VISIBLE
            binding.start.setImageDrawable(getDrawable(R.drawable.pause))
            val hours = binding.saat.text.toString().toIntOrNull() ?: 0
            val minutes = binding.dakika.text.toString().toIntOrNull() ?: 0
            timeLeft = (hours * 3600000 + minutes * 60000).toLong()
            hideKeyboard(binding.root)
            sayacBaslat(timeLeft)
        }

        binding.pauseButton.setOnClickListener {
            if (isRunning) {
                countDownTimer?.cancel()
                isRunning = false
                binding.sayacSayim.text = "00:00:00"
                binding.saat.visibility = View.VISIBLE
                binding.dakika.visibility = View.VISIBLE
                binding.startButton.visibility = View.VISIBLE
                binding.pauseButton.visibility = View.GONE
                binding.buttonTYT.visibility = View.VISIBLE
                binding.buttonAYT.visibility = View.VISIBLE
                binding.saat.text.clear()
                binding.dakika.text.clear()
                binding.start.setImageDrawable(getDrawable(R.drawable.start))
                hideKeyboard(binding.root)
            }
        }

        binding.buttonTYT.setOnClickListener {
            timeLeft = (9900000).toLong()
            binding.saat.visibility = View.GONE
            binding.dakika.visibility = View.GONE
            binding.startButton.visibility = View.GONE
            binding.pauseButton.visibility = View.VISIBLE
            binding.buttonTYT.visibility = View.GONE
            binding.buttonAYT.visibility = View.GONE
            binding.start.setImageDrawable(getDrawable(R.drawable.pause))
            hideKeyboard(binding.root)
            sayacBaslat(timeLeft)
        }

        binding.buttonAYT.setOnClickListener {
            timeLeft = (10800000).toLong()
            binding.saat.visibility = View.GONE
            binding.dakika.visibility = View.GONE
            binding.startButton.visibility = View.GONE
            binding.pauseButton.visibility = View.VISIBLE
            binding.buttonTYT.visibility = View.GONE
            binding.buttonAYT.visibility = View.GONE
            binding.start.setImageDrawable(getDrawable(R.drawable.pause))
            hideKeyboard(binding.root)
            sayacBaslat(timeLeft)
        }

    }

    fun sayacBaslat(totalMillis: Long) {
        countDownTimer = object : CountDownTimer(totalMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                val seconds = (millisUntilFinished / 1000) % 60
                val minutes = (millisUntilFinished / 60000) % 60
                val hours = (millisUntilFinished / 3600000)
                binding.sayacSayim.text =
                    String.format("%02d:%02d:%02d", hours, minutes, seconds)
            }

            override fun onFinish() {
                binding.sayacSayim.text = "00:00:00"
                isRunning = false
                binding.saat.visibility = View.VISIBLE
                binding.dakika.visibility = View.VISIBLE
                binding.startButton.visibility = View.VISIBLE
                binding.pauseButton.visibility = View.GONE
                binding.start.setImageDrawable(getDrawable(R.drawable.start))
            }
        }.start()
        isRunning = true
    }

    fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}