package com.demal.repository

import com.demal.model.data.entity.user.LoginResponse

interface Repository {

    suspend fun login(email: String, pass: String): LoginResponse
}
