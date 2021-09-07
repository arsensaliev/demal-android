package com.demal.repository.repository

import com.demal.model.data.entity.user.User
import com.demal.model.data.entity.user.UserUpdate

interface UserRepository {
    suspend fun login(email: String, password: String): User
    suspend fun myUser(): User
    suspend fun update( id: Int, user: UserUpdate)
}