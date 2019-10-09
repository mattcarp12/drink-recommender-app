package org.matt.drink_recommender_app.repository

import io.reactivex.Single
import org.matt.drink_recommender_app.model.Answers
import org.matt.drink_recommender_app.model.Question
import org.matt.drink_recommender_app.model.Questions
import org.matt.drink_recommender_app.repository.Network.DrinkRecommenderService

class Repository {

    val drinkRecommenderService = DrinkRecommenderService()

    fun getQuestions(): Questions {
        return getMockQuestions()
    }

    fun getMockQuestions(): Questions {
        val q1 = Question("gender", "What is your gender?", listOf("male", "female"))
        val q2 = Question("pets", "How many pets do you have?", listOf("0", "1", "2+"))
        return Questions(listOf(q1, q2))
    }

    fun getRecommendedDrink(answers: Answers): Single<String> {
        return drinkRecommenderService.getRecommendedDrink(answers)
    }

}