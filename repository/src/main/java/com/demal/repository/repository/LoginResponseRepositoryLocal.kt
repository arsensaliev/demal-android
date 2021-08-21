package com.demal.repository.repository

import com.demal.model.data.entity.user.LoginResponse
import com.demal.model.data.entity.user.User

interface LoginResponseRepositoryLocal {
    suspend fun saveResponse(response: LoginResponse)
    fun getUserToken(): String?
    suspend fun getUser(): User?
    suspend fun removeUser()
}