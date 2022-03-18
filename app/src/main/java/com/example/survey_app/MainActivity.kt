package com.example.survey_app
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.surveyapp.SurveyViewModel

const val EXTRA_GET_YES_COUNT = "com.example.survey_app.YES_COUNT"
const val EXTRA_GET_NO_COUNT = "com.example.survey_app.NO_COUNT"

class MainActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var yesButton: Button
    private lateinit var noButton: Button
    private lateinit var yesCounterTextView: TextView
    private lateinit var noCounterTextView: TextView
    private lateinit var resultsButton: Button

    private val questionBank = listOf(
        R.string.question_1,
        R.string.question_2,
        R.string.question_3,
        R.string.question_4,
        R.string.question_5,
        R.string.question_6,
        R.string.question_7
    )

    private var currentIndex = 0

    // lazy view model
    private val surveyViewModel: SurveyViewModel by lazy {
        ViewModelProvider(this).get(SurveyViewModel::class.java)
    }

    private val surveyResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result -> handleSurveyResult(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionTextView = findViewById(R.id.question_textview)
        yesButton = findViewById(R.id.yes_button)
        noButton = findViewById(R.id.no_button)
        yesCounterTextView = findViewById(R.id.yesCounter)
        noCounterTextView = findViewById(R.id.noCounter)
        resultsButton = findViewById(R.id.results_button)




        updateQuestion()

        yesButton.setOnClickListener {

            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
            surveyViewModel.increaseYesCount()

        }

        noButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
            surveyViewModel.increaseNoCount()

        }

        resultsButton.setOnClickListener{
            getResults()
        }

    }

    private fun getResults() {
        Intent(this, SurveyResultActivity::class.java).apply {
            this.putExtra(EXTRA_GET_YES_COUNT, surveyViewModel.yesCount)
            this.putExtra(EXTRA_GET_NO_COUNT, surveyViewModel.noCount)
            surveyResultLauncher.launch(this)
        }
    }


    private fun handleSurveyResult(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            val intent = result.data
            val keepResults = intent?.getBooleanExtra(EXTRA_KEEP_RESULTS, true) ?: true
            val message = if (keepResults) {
                getString(R.string.keeping_results_message)
            } else {

                getString(R.string.clearing_results_message)
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        } else if (result.resultCode == RESULT_CANCELED) {
            Toast.makeText(this, getString(R.string.canceled_results_message), Toast.LENGTH_SHORT).show()
        }
    }


    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex]
        questionTextView.setText(questionTextResId)
    }

}
