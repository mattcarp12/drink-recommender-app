package org.matt.drink_recommender_app.repository

import org.matt.drink_recommender_app.model.Question
import org.matt.drink_recommender_app.model.Questions

class Repository {

    fun getQuestions(): Questions {
        return getMockQuestions()
    }

    fun getMockQuestions(): Questions {
        val q1 = Question("gender", "What is your gender?", listOf("Male", "Female"))
        val q2 = Question("pets", "How many pets do you have?", listOf("0", "1", "2", "3+"))
        val q3 = Question("occupation", "What is your occupation?", listOf("Retail", "Construction", "Management"))
        val q4 = Question("personality", "What best describes your personality?", listOf("Ambitious", "Wild Child", "Life of the party"))
        return Questions(listOf(q1, q2, q3, q4))
    }



}