package com.demal.repository

import com.demal.model.data.user.entity.AuthenticationResult
import com.demal.model.data.user.entity.AuthenticationServer
import com.demal.repository.api.ApiService
import retrofit2.Retrofit

class RetrofitImplementation(val retrofit: Retrofit) : Repository {

    override suspend fun login(email: String, pass: String): AuthenticationResult {
        var auth = AuthenticationServer(email, pass)
        return retrofit.create(ApiService::class.java).login(auth).await()
    }

    companion object {
        const val BASE_URL_LOCATIONS = "https://demal-api.herokuapp.com/api/v1/"
    }
}
