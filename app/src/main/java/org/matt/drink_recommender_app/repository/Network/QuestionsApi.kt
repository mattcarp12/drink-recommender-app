package org.matt.drink_recommender_app.repository.Network

import io.reactivex.Single
import org.matt.drink_recommender_app.model.Question
import retrofit2.http.GET

interface QuestionsApi {

    @GET("/question")
    fun getQuestions(): Single<List<Question>>

}