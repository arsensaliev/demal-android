package com.demal.repository

import com.demal.model.dto.auth.AuthenticationResultDto

interface Repository {

    suspend fun getAuthData(email: String,pass: String): AuthenticationResultDto
}
