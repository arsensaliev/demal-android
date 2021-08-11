package com.demal.repository.data_sources

import com.demal.model.data.entity.user.LoginRequest
import com.demal.model.data.entity.user.LoginResponse

interface RemoteDataSource {
    suspend fun login(auth: LoginRequest): LoginResponse
}