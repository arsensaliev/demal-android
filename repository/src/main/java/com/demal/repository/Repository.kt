package com.demal.repository

import com.demal.model.dto.auth.AuthenticationResultDto

interface Repository {

    suspend fun getAuthData(login: String,pass: String): AuthenticationResultDto
}
