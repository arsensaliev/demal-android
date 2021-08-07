package com.demal.repository.api

import com.demal.model.dto.auth.AuthenticationResultDto
import com.demal.model.dto.auth.AuthenticationServerDto
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("users/login")
    fun getAuthData(@Body auth : AuthenticationServerDto): Deferred<AuthenticationResultDto>

}
