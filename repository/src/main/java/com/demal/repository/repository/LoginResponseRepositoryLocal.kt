package com.demal.repository.repository

import com.demal.model.data.entity.user.LoginResponse
import com.demal.model.data.entity.user.User

interface LoginResponseRepositoryLocal {
    suspend fun saveResponse(response: LoginResponse)
    suspend fun removeUser()
    fun getUserToken(): String?
    fun getUser(): User?
}