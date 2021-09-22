package com.demal.repository.repository

import com.demal.model.data.entity.user.*
import com.demal.repository.data_sources.RemoteDataSource

class UserRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val userRepositoryLocal: UserRepositoryLocal,
    private val toursRepository: ToursRepository
) : UserRepository {

    override suspend fun login(email: String, password: String): User {
        val response = remoteDataSource.login(LoginRequest(email, password))
        processLoginResponse(response)

        return response.user
    }

    override suspend fun myUser() =
        remoteDataSource.myUser().user.apply {
            val token = userRepositoryLocal.getUserToken()
            token?.let {
                userRepositoryLocal.saveResponse(
                    LoginResponse(Auth(token), this)
                )
            }
        }

    override suspend fun register(registerDto: RegisterDto): User {
        val response = remoteDataSource.register(registerDto)
        processLoginResponse(response)
        return response.user
    }

    override suspend fun logOut() {
        userRepositoryLocal.removeUser()
    }


    private suspend fun processLoginResponse(response: LoginResponse) {
        userRepositoryLocal.saveResponse(response)
        response.user.id?.let { id ->
            toursRepository.getFavoriteTours(id)
        }
    }

    override suspend fun update(id: Int, user: UserUpdate) {
        remoteDataSource.updateUser(id, user)
    }

    override suspend fun updateAvatar(fileByte: ByteArray?) {
        remoteDataSource.updateAvatar(fileByte)
    }
}