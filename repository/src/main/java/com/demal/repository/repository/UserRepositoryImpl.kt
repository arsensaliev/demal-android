package com.demal.repository.repository

import com.demal.model.data.entity.user.LoginRequest
import com.demal.model.data.entity.user.User
import com.demal.repository.data_sources.RemoteDataSource

class UserRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : UserRepository {
    override suspend fun login(email: String, password: String): User {
        val response = remoteDataSource.login(LoginRequest(email, password))
        //TODO: Save token to sharedPreferences
        return response.user
    }
}