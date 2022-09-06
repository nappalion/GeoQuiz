package com.bignerdranch.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var questionTextView: TextView

    private val TAG = "MainActivity"


    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))

    private var currentIndex = 0
    private var questionsAnswered = mutableListOf<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate(Bundle?) called")

        questionBank.forEach{ _ -> questionsAnswered += false }

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.prev_button)
        questionTextView = findViewById(R.id.question_text_view)

        updateAnswerButtons()

        questionTextView.setOnClickListener { view: View ->
            changeQuestionIndex()
            updateQuestion()
        }

        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
            questionsAnswered[currentIndex] = true
            updateAnswerButtons()
        }

        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
            questionsAnswered[currentIndex] = true
            updateAnswerButtons()
        }

        nextButton.setOnClickListener {
            changeQuestionIndex()
            updateQuestion()
            updateAnswerButtons()
        }

        prevButton.setOnClickListener {
            changeQuestionIndex(false)
            updateQuestion()
            updateAnswerButtons()
        }

        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateAnswerButtons() {
        if (!questionsAnswered[currentIndex]) {
            enableAnswerButtons()
        } else {
            disableAnswerButtons()
        }
    }

    private fun disableAnswerButtons() {
        trueButton.isEnabled = false
        falseButton.isEnabled = false
    }

    private fun enableAnswerButtons() {
        trueButton.isEnabled = true
        falseButton.isEnabled = true
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun changeQuestionIndex(goNext: Boolean = true) {
        var traverseIndex = if (goNext) currentIndex + 1 else currentIndex - 1
        currentIndex = if (traverseIndex >= 0) (traverseIndex) % questionBank.size else (questionBank.size - (traverseIndex % questionBank.size) - 2)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()
    }
}