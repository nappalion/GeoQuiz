package com.bignerdranch.android.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {


    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))

    private var currentIndex = 0
    private var questionsAnswered = mutableListOf<Boolean>()
    private var questionsCorrect = 0

    init {
        questionBank.forEach{ _ -> questionsAnswered += false }
    }

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    val getIndex: Int
        get() = currentIndex

    val getQuestionBankSize: Int
        get() = questionBank.size

    val getQuestionsCorrect: Int
        get() = questionsCorrect

    val currentButtonStatus: Boolean
        get() = questionsAnswered[currentIndex]

    val allQuestionsAnswered: Boolean
        get() = questionsAnswered.all{ it }

    fun setCurrentIndex(newIndex: Int) {
        currentIndex = newIndex
    }

    fun updateQuestionsCorrect() { questionsCorrect += 1 }

    fun updateButtonStatus(status: Boolean) {
        questionsAnswered[currentIndex] = status
    }


}