package org.matt.drink_recommender_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.matt.drink_recommender_app.model.Answers
import org.matt.drink_recommender_app.model.Questions

class ViewModelQuestions : ViewModel() {

    val questions = Questions()
    val answers = Answers()
    var currentQuestionNumber = 0

    val currentQuestionNumberLiveData: MutableLiveData<Int> = MutableLiveData(0)
    val currentQuestionLiveData: MutableLiveData<String> =
        MutableLiveData(questions.questionList[0].questionText)


    fun nextQuestion(): Boolean {
        if (currentQuestionNumber == questions.size()) return false
        currentQuestionNumber++
        update()
        return true
    }

    fun prevQuestion(): Boolean {
        if (currentQuestionNumber == 0) return false
        currentQuestionNumber--
        update()
        return true
    }

    fun update() {
        currentQuestionNumberLiveData.value = currentQuestionNumber
        currentQuestionLiveData.value = questions.questionList[currentQuestionNumber].questionText
    }

    /*fun setAnswer(answer: String) {
        val key = questions.keys[questions.currentQuestionNumber]
        answers.set(key, answer)
    }*/
}