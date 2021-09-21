package com.demal.repository.repository

import com.demal.model.data.entity.user.RegisterDto
import com.demal.model.data.entity.user.User

interface UserRepository {
    suspend fun login(email: String, password: String): User
    suspend fun myUser(): User
    suspend fun register(registerDto: RegisterDto): User
}