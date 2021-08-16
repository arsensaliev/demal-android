package com.demal.repository.api

import com.demal.model.data.entity.tours.Tour
import com.demal.model.data.entity.user.LoginResponse
import com.demal.model.data.entity.user.LoginRequest
import com.demal.model.data.entity.user.User
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface ApiService {

    @POST("api/$API_VERSION/users/login")
    fun login(@Body auth: LoginRequest): Deferred<LoginResponse>

    @GET("api/$API_VERSION/users/me")
    fun myUser(): Deferred<User>

    @GET("api/$API_VERSION/tours")
    fun getTours(
        @Query("sortBy") sortBy: String,
        @Query("order") order: String,
    ): Deferred<List<Tour>>

    @GET("api/$API_VERSION/tours/{id}")
    fun getTourById(
        @Path("id") id: Int,
    ): Deferred<Tour>

    companion object {
        private const val API_VERSION = "v1"
    }
}
