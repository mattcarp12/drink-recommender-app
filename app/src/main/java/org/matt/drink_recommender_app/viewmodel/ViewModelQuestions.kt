package org.matt.drink_recommender_app.viewmodel

import android.app.Application
import android.view.View
import android.widget.RadioGroup
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

class ViewModelQuestions(application: Application) : AndroidViewModel(application) {
    var current = 1

    val keys = listOf<String>(
        "gender"
        , "pets"
        , "occupation"
        , "personality"
    )

    val questions = mapOf<String, String>(
        "gender" to "What is your gender?"
        , "pets" to "How many pets do you have?"
        , "occupation" to "What is your occupation?"
        , "personality" to "What best describes your personality?"
    )

    var answers = HashMap<String, String>()

    /*val responseViews = mapOf<String, View>(
        "gender" to genderView
    )

    val genderView: RadioGroup = RadioGroup(application)
*/


}