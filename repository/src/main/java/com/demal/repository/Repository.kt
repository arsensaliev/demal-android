package com.demal.repository

import com.demal.model.data.entity.AuthenticationResult

interface Repository {

    suspend fun login(email: String, pass: String): AuthenticationResult
}
