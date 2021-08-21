package com.demal.repository.repository

import com.demal.model.data.entity.user.LoginResponse
import com.demal.repository.data_sources.preferences.GeneralPreferencesDataSource

class LoginResponseRepositoryLocalImpl(
    private val preferencesDataSource: GeneralPreferencesDataSource
) : LoginResponseRepositoryLocal {

    override suspend fun saveResponse(response: LoginResponse) {
        preferencesDataSource.putParcelable(RESPONSE_KEY, response)
    }

    override fun getUserToken() =
        getResponse()?.auth?.token

    override suspend fun removeUser() {
        preferencesDataSource.remove(RESPONSE_KEY)
    }

    override suspend fun getUser() =
        getResponse()?.user

    private fun getResponse() = preferencesDataSource.getParcelable<LoginResponse>(RESPONSE_KEY)

    companion object {
        private const val RESPONSE_KEY = "RESPONSE_KEY"
    }
}