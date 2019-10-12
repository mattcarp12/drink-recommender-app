package org.matt.drink_recommender_app.repository.Network

import io.reactivex.Single
import org.matt.drink_recommender_app.model.Question
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class QuestionsService {
    val baseUrl = "http://10.0.2.2:8080"

    val questionsApi: QuestionsApi =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(QuestionsApi::class.java)

    fun getQuestions(): Single<List<Question>> {
        return questionsApi.getQuestions()
    }
}