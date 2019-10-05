package org.matt.drink_recommender_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.matt.drink_recommender_app.model.Answers
import org.matt.drink_recommender_app.model.Questions

class ViewModelQuestions : ViewModel() {

    val questions = Questions()
    val answers = Answers()

    val currentQuestionNumber: MutableLiveData<Int> = MutableLiveData(0)
    val currentQuestion: MutableLiveData<String> =
        MutableLiveData(questions.getCurrentQuestion())


    fun nextQuestion() {
        questions.next()
        update()
    }

    fun prevQuestion() {
        questions.prev()
        update()
    }

    fun update() {
        currentQuestionNumber.value = questions.currentQuestionNumber
        currentQuestion.value = questions.getCurrentQuestion()
    }

    fun setAnswer(answer: String) {
        val key = questions.keys[questions.currentQuestionNumber]
        answers.set(key, answer)
    }
}