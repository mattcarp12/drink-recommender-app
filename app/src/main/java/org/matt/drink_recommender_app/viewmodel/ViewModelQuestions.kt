package org.matt.drink_recommender_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.matt.drink_recommender_app.model.Answers
import org.matt.drink_recommender_app.repository.Repository

class ViewModelQuestions : ViewModel() {

    val repository: Repository = Repository()
    val questions = repository.getQuestions()
    val answers = Answers()
    var currentQuestionNumber = 0

    val currentQuestionNumberLiveData: MutableLiveData<Int> = MutableLiveData(currentQuestionNumber)
    val currentQuestionLiveData: MutableLiveData<String> =
        MutableLiveData(questions.questionList[currentQuestionNumber].questionText)
    val currentQuestionResponsesLiveData: MutableLiveData<List<String>> =
        MutableLiveData(questions.questionList[currentQuestionNumber].responses)


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

    fun getAnswer(): String? =
        answers.get(questions.questionList[currentQuestionNumber].questionName)

}