package com.example.surveyapp

import androidx.lifecycle.ViewModel

class SurveyViewModel: ViewModel() {

    var yesCount = 0
    var noCount = 0


    fun increaseYesCount(){
        yesCount += 1
    }

    fun increaseNoCount(){
        noCount += 1
    }

    fun resetCounters(){
        yesCount = 0
        noCount = 0
    }
}