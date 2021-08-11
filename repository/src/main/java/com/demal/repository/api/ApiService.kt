package com.demal.repository.api

import com.demal.model.data.entity.user.LoginResponse
import com.demal.model.data.entity.user.LoginRequest
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("users/login")
    fun login(@Body auth: LoginRequest): Deferred<LoginResponse>

}
