package com.demal.repository.api

import com.demal.model.data.entity.AuthenticationResult
import com.demal.model.data.entity.AuthenticationServer
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("users/login")
    fun login(@Body auth: AuthenticationServer): Deferred<AuthenticationResult>

}
