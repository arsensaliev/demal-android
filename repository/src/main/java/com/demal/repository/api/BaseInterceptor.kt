package com.demal.repository.api

import com.demal.model.data.exceptions.NoAuthException
import kotlinx.coroutines.CancellationException
import okhttp3.Interceptor
import okhttp3.Response

class BaseInterceptor private constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val responseCode = response.code()
        processResponseCode(responseCode)
        return response
    }

    private fun processResponseCode(responseCode: Int) {
        if (responseCode == 401)
            throw NoAuthException()
    }

    companion object {

        val interceptor: BaseInterceptor
            get() = BaseInterceptor()
    }
}