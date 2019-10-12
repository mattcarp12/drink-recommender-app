package org.matt.drink_recommender_app.repository.Network

import io.reactivex.Completable
import org.matt.drink_recommender_app.model.UserResponse
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UserResponseService {
    val baseUrl = "http://10.0.2.2:8080"

    val userResponseApi: UserResponseApi =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(UserResponseApi::class.java)

    fun submitUserResponse(userResponse: UserResponse): Completable {
        return userResponseApi.postAnswersAndResponse(userResponse)
    }
}