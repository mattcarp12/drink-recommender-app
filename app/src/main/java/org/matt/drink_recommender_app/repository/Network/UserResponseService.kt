package org.matt.drink_recommender_app.repository.Network

import io.reactivex.Completable
import io.reactivex.Single
import org.matt.drink_recommender_app.model.Answers
import org.matt.drink_recommender_app.model.UserResponse
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class UserResponseService {
    val baseUrl = "http://10.0.2.2:8080"

    val userResponseApi: UserResponseApi =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(UserResponseApi::class.java)

    fun submitUserResponse(userResponse: UserResponse): Completable {
        return userResponseApi.postAnswersAndResponse(userResponse)
    }
}