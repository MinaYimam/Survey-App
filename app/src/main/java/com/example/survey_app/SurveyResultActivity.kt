package com.example.survey_app
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


const val EXTRA_KEEP_RESULTS = "com.example.survey_app.EXTRA_KEEP_RESULTS"

class SurveyResultActivity : AppCompatActivity() {

    private lateinit var yesCounterTextView: TextView
    private lateinit var noCounterTextView: TextView
    private lateinit var resetButton: Button
    private lateinit var continueButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_result)

        yesCounterTextView = findViewById(R.id.yes_counter)
        noCounterTextView = findViewById(R.id.no_counter)
        resetButton = findViewById(R.id.reset_button)
        continueButton = findViewById(R.id.continue_button)


        val yesCount = intent.getIntExtra(EXTRA_GET_YES_COUNT, 0)
        val noCount = intent.getIntExtra(EXTRA_GET_NO_COUNT, 0)

        yesCounterTextView.text = yesCount.toString().plus(" yes vote(s)")
        noCounterTextView.text = noCount.toString().plus(" no vote(s)")


        resetButton.setOnClickListener {

            keepResults(false)
        }

        continueButton.setOnClickListener {

            keepResults(true)
        }

    }



    private fun keepResults(doKeepResults: Boolean) {
        Intent().apply {
            putExtra(EXTRA_KEEP_RESULTS, doKeepResults)
            setResult(RESULT_OK, this)
            finish()
        }
        }
}

