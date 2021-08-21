package com.demal.repository.repository

import com.demal.model.data.entity.user.Auth
import com.demal.model.data.entity.user.LoginRequest
import com.demal.model.data.entity.user.LoginResponse
import com.demal.model.data.entity.user.User
import com.demal.repository.data_sources.RemoteDataSource

class UserRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val responseRepository: LoginResponseRepositoryLocal,
    private val toursRepository: ToursRepository
) : UserRepository {
    override suspend fun login(email: String, password: String): User {
        val response = remoteDataSource.login(LoginRequest(email, password))
        responseRepository.saveResponse(response)
        response.user.id?.let { id ->
            toursRepository.getFavoriteTours(id)
        }

        return response.user
    }

    override suspend fun myUser() =
        remoteDataSource.myUser().apply {
            val token = responseRepository.getUserToken()
            token?.let {
                responseRepository.saveResponse(
                        LoginResponse(Auth(token), this)
                    )
            }
        }
}