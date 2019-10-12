package org.matt.drink_recommender_app.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.matt.drink_recommender_app.model.Answers
import org.matt.drink_recommender_app.model.Question
import org.matt.drink_recommender_app.repository.Repository

class AppViewModel : ViewModel() {

    var TAG: String = "AppViewModel"
    val repository: Repository = Repository()
    lateinit var questionList: List<Question>
    val answers = Answers()
    var currentQuestionNumber = 0


    val currentQuestionNumberLiveData: MutableLiveData<Int> = MutableLiveData(currentQuestionNumber)

    val currentQuestionLiveData: MutableLiveData<String> =
        MutableLiveData(questionList[currentQuestionNumber].questionText)

    val currentQuestionChoicesLiveData: MutableLiveData<List<String>> =
        MutableLiveData(questionList[currentQuestionNumber].choices)

    val recommendedDrinkLiveData: MutableLiveData<String> = MutableLiveData()


    fun nextQuestion(): Boolean {
        if (currentQuestionNumber == questionList.size - 1) return false
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
        currentQuestionLiveData.value = questionList[currentQuestionNumber].questionText
        currentQuestionChoicesLiveData.value =
            questionList[currentQuestionNumber].choices
    }

    fun setAnswer(answer: String) {
        answers.set(questionList[currentQuestionNumber].questionName, answer)
    }

    fun setAnswer(question: String, answer: String) {
        answers.set(question, answer)
    }

    fun getAnswer(): String? =
        answers.get(questionList[currentQuestionNumber].questionName)

    fun getQuestions() {
        repository
            .getQuestions()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleQuestionResponse, this::handleError)
    }

    fun handleQuestionResponse(response: List<Question>) {
        questionList = response
    }

    fun getDrinkRecommendation() {
        repository
            .getRecommendedDrink(answers)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse, this::handleError)
    }

    fun sendResponse(preferredDrink: String) {
        /*repository
            .submitResponse(UserResponse(preferredDrink, answers))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::onResponseComplete, this::handleError)*/
    }

    fun handleResponse(response: String) {
        recommendedDrinkLiveData.value = response
    }

    fun handleError(error: Throwable) {
        Log.d(TAG, error.toString())
    }

    fun onResponseComplete() {
        // TODO: something
    }
}