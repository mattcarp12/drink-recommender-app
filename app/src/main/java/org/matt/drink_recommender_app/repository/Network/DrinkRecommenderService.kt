package org.matt.drink_recommender_app.repository.Network

import io.reactivex.Single
import org.matt.drink_recommender_app.model.Answers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class DrinkRecommenderService {

    val baseUrl = "http://10.0.2.2:8080"

    val drinkRecommenderApi: DrinkRecommenderApi =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            //.addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(DrinkRecommenderApi::class.java)

    fun getRecommendedDrink(answers: Answers): Single<String> {
        return drinkRecommenderApi.getRecommendation(answers.answerMap)
    }
}