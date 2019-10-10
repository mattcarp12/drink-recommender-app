package org.matt.drink_recommender_app.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.matt.drink_recommender_app.model.Answers
import org.matt.drink_recommender_app.repository.Repository

class AppViewModel : ViewModel() {

    var TAG: String = "AppViewModel"
    val repository: Repository = Repository()
    val questions = repository.getQuestions()
    val answers = Answers()
    var currentQuestionNumber = 0

    val currentQuestionNumberLiveData: MutableLiveData<Int> = MutableLiveData(currentQuestionNumber)
    val currentQuestionLiveData: MutableLiveData<String> =
        MutableLiveData(questions.questionList[currentQuestionNumber].questionText)
    val currentQuestionResponsesLiveData: MutableLiveData<List<String>> =
        MutableLiveData(questions.questionList[currentQuestionNumber].responses)
    val recommendedDrinkLiveData: MutableLiveData<String> = MutableLiveData()


    fun nextQuestion(): Boolean {
        if (currentQuestionNumber == questions.size() - 1) return false
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
        currentQuestionResponsesLiveData.value =
            questions.questionList[currentQuestionNumber].responses
    }

    fun setAnswer(answer: String) {
        answers.set(questions.questionList[currentQuestionNumber].questionName, answer)
    }

    fun setAnswer(question: String, answer: String) {
        answers.set(question, answer)
    }

    fun getAnswer(): String? =
        answers.get(questions.questionList[currentQuestionNumber].questionName)

    fun getResponse() {
        repository
            .getRecommendedDrink(answers)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse, this::handleError)
    }

    fun handleResponse(response: String) {
        recommendedDrinkLiveData.value = response
    }

    fun handleError(error: Throwable) {
        Log.d(TAG, error.toString())
    }
}