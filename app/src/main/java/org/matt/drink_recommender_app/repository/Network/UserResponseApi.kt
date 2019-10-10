package org.matt.drink_recommender_app.repository.Network

import io.reactivex.Completable
import org.matt.drink_recommender_app.model.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserResponseApi {

    @POST("/submit")
    fun postAnswersAndResponse(
        @Body userResponse: UserResponse
    ): Completable

}