package com.demal.repository.repository

import com.demal.model.data.entity.user.LoginRequest
import com.demal.model.data.entity.user.User
import com.demal.repository.data_sources.RemoteDataSource
import com.demal.repository.data_sources.TokenRepository

class UserRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val tokenRepository : TokenRepository
) : UserRepository {
    override suspend fun login(email: String, password: String): User {
        val response = remoteDataSource.login(LoginRequest(email, password))
        tokenRepository.saveToken(response.auth.token)
        return response.user
    }

    override suspend fun myUser() = remoteDataSource.myUser()
}