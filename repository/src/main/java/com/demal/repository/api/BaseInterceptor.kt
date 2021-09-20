package com.demal.repository.api

import com.demal.model.data.exceptions.BadRequestException
import com.demal.model.data.exceptions.NoAuthException
import com.demal.repository.repository.UserRepositoryLocal
import okhttp3.Interceptor
import okhttp3.Response

class BaseInterceptor(
    private val userRepositoryLocal: UserRepositoryLocal
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = userRepositoryLocal.getUserToken()

        val newRequest = token?.let {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } ?: chain.request()

        val response = chain.proceed(newRequest)
        val responseCode = response.code()
        val responseMessage = response.message()
        processResponseCode(responseCode, responseMessage)
        return response
    }

    private fun processResponseCode(responseCode: Int, responseMessage: String) {
        if (responseCode == 401) {
            throw NoAuthException(responseMessage)
        }

        if (responseCode == 400) {
            throw BadRequestException(responseMessage)
        }
    }
}