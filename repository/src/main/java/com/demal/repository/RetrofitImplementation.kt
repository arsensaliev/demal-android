package com.demal.repository

import com.demal.model.data.entity.user.LoginResponse
import com.demal.model.data.entity.user.LoginRequest
import com.demal.repository.api.ApiService
import retrofit2.Retrofit

class RetrofitImplementation(val retrofit: Retrofit) : Repository {

    override suspend fun login(email: String, pass: String): LoginResponse {
        var auth = LoginRequest(email, pass)
        return retrofit.create(ApiService::class.java).login(auth).await()
    }

    companion object {
        const val BASE_URL_LOCATIONS = "https://demal-api.herokuapp.com/api/v1/"
    }
}
