package com.demal.repository.api

import com.demal.model.data.exceptions.NoAuthException
import com.demal.repository.data_sources.TokenRepository
import okhttp3.Interceptor
import okhttp3.Response

class BaseInterceptor(
    private val tokenRepository: TokenRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenRepository.getToken()

        val newRequest = token?.let {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } ?: chain.request()

        val response = chain.proceed(newRequest)
        val responseCode = response.code()
        processResponseCode(responseCode)
        return response
    }

    private fun processResponseCode(responseCode: Int) {
        if (responseCode == 401)
            throw NoAuthException()
    }
}