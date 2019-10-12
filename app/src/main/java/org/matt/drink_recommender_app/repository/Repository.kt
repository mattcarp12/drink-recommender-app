package org.matt.drink_recommender_app.repository

import io.reactivex.Completable
import io.reactivex.Single
import org.matt.drink_recommender_app.model.Answers
import org.matt.drink_recommender_app.model.Question
import org.matt.drink_recommender_app.model.Questions
import org.matt.drink_recommender_app.model.UserResponse
import org.matt.drink_recommender_app.repository.Network.DrinkRecommenderService
import org.matt.drink_recommender_app.repository.Network.QuestionsService
import org.matt.drink_recommender_app.repository.Network.UserResponseService

class Repository {

    val drinkRecommenderService = DrinkRecommenderService()
    val userResponseService = UserResponseService()
    val questionsService = QuestionsService()

    fun getQuestions(): Single<List<Question>> {
        return questionsService.getQuestions()
    }

    fun getMockQuestions(): Questions {
        val q1 = Question("gender", "What is your gender?", listOf("male", "female"))
        val q2 = Question("pets", "How many pets do you have?", listOf("0", "1", "2+"))
        return Questions(listOf(q1, q2))
    }

    fun getRecommendedDrink(answers: Answers): Single<String> {
        return drinkRecommenderService.getRecommendedDrink(answers)
    }

    fun submitResponse(userResponse: UserResponse): Completable {
        return userResponseService.submitUserResponse(userResponse)
    }

}