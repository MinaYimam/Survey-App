package com.example.survey_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.surveyapp.SurveyViewModel


const val EXTRA_YES_COUNT = "com.example.surveyapp2.yes_count"
const val EXTRA_NO_COUNT = "com.example.surveyapp2.no_count"

class MainActivity : AppCompatActivity() {
    private lateinit var yesButton: Button
    private lateinit var noButton: Button
    private lateinit var noCounterText: TextView
    private lateinit var yesCounterText: TextView
    private lateinit var reset_button : Button

    private val surveyiewmodel: SurveyViewModel by lazy {
        ViewModelProvider(this).get(SurveyViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        yesButton = findViewById(R.id.yes_button)
        noButton = findViewById(R.id.no_button)
        noCounterText = findViewById(R.id.noCounter)
        yesCounterText = findViewById(R.id.yesCounter)
        reset_button = findViewById(R.id.reset_button)
        yesButton.setOnClickListener {
            increaseYesCount()
        }
        noButton.setOnClickListener {
            increaseNoCount()
        }
        reset_button.setOnClickListener {
            resetCounters()
        }
    }

    private fun increaseYesCount() {
        surveyiewmodel.increaseYesCount()
        updateCounters()
    }
    private fun increaseNoCount() {
        surveyiewmodel.increaseNoCount()
        updateCounters()
    }
    private fun resetCounters() {
        surveyiewmodel.resetCounters()
        updateCounters()
    }
    private fun updateCounters() {
        val yesCount = surveyiewmodel.yesCount
        val noCount = surveyiewmodel.noCount
        yesCounterText.text = yesCount.toString()
        noCounterText.text = noCount.toString()

    }
}
