package org.matt.drink_recommender_app.repository.Network

import io.reactivex.Completable
import io.reactivex.Single
import org.matt.drink_recommender_app.model.Questions
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface DrinkRecommenderApi {

    @GET("/questions")
    fun getQuestions(): Single<Questions>

    @GET("/drink-recommender")
    fun getRecommendation(@QueryMap answers: Map<String, String>): Single<String>


}