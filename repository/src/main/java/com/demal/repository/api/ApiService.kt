package com.demal.repository.api

import com.demal.model.data.entity.user.LoginResponse
import com.demal.model.data.entity.user.LoginRequest
import com.demal.model.data.entity.user.User
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("api/v1/users/login")
    fun login(@Body auth: LoginRequest): Deferred<LoginResponse>

    @GET("api/v1/users/me")
    fun myUser(): Deferred<User>

}
