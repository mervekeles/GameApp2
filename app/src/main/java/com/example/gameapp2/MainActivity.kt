package com.example.gameapp2

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var gameScoreTV: TextView
    private lateinit var timeLeftTV: TextView
    private lateinit var tapMeButton: Button

    private var score = 0


    private var gameStarted = false
    private lateinit var countDownTimer: CountDownTimer
    private var initialCountDown: Long = 60000
    private var countDownInterval: Long= 1000
    private var timeLeft = 60


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val tapMeButton: Button = findViewById(R.id.tapme)

        tapMeButton = findViewById(R.id.tapme)

        gameScoreTV = findViewById(R.id.gameScore)
        timeLeftTV = findViewById(R.id.timeLeft)

        tapMeButton.setOnClickListener {
           // Toast.makeText(this, "Tapped !!!!", Toast.LENGTH_SHORT).show()
            incrementScore()
        }

        resetGame()
    }

    private fun incrementScore(){
        score++

        //val newScore = "Your Score: $score"
        val newScore = getString(R.string.your_score, score)
        gameScoreTV.text = newScore

        if(!gameStarted)
        {
            startGame()
        }
    }

    private fun resetGame(){
        score = 0
        val initialScore = getString(R.string.your_score, score)
        gameScoreTV.text = initialScore
        val initialTimeLeft = getString(R.string.time_left, 60)
        timeLeftTV.text = initialTimeLeft

        countDownTimer = object: CountDownTimer(initialCountDown,countDownInterval){
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished.toInt() / 1000
                val timeLeftString = getString(R.string.time_left, timeLeft)
                timeLeftTV.text = timeLeftString

            }

            override fun onFinish() {
                endGame()
            }
        }
        gameStarted = false



    }

    private  fun startGame(){
        countDownTimer.start()
        gameStarted = true

    }
    private  fun endGame(){
        Toast.makeText(this, getString(R.string.game_over_message, score), Toast.LENGTH_SHORT).show()
        resetGame()
    }

}