package com.demal.repository.api

import com.demal.model.data.user.entity.AuthenticationResult
import com.demal.model.data.user.entity.AuthenticationServer
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("users/login")
    fun login(@Body auth: AuthenticationServer): Deferred<AuthenticationResult>

}
