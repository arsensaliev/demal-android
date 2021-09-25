package com.demal.repository.repository

import com.demal.model.data.entity.user.LoginResponse
import com.demal.repository.data_sources.preferences.GeneralPreferencesDataSource

class UserRepositoryLocalImpl(
    private val preferencesDataSource: GeneralPreferencesDataSource
) : UserRepositoryLocal {

    override suspend fun saveResponse(response: LoginResponse) {
        preferencesDataSource.putParcelable(RESPONSE_KEY, response)
    }

    override fun getUserToken() =
        getResponse()?.auth?.token

    override suspend fun removeUser() {
        preferencesDataSource.remove(RESPONSE_KEY)
        preferencesDataSource.putString(RESPONSE_KEY, REMOVED)
    }

    override fun isFirstLaunch(): Boolean {
        val responseStr = try {
            preferencesDataSource.getString(RESPONSE_KEY)
        } catch (e: Exception) {
            USER_EXISTS
        }

        return responseStr == null
    }

    override fun getUser() =
        getResponse()?.user

    private fun getResponse() = try {
        preferencesDataSource.getParcelable(RESPONSE_KEY, LoginResponse::class.java)
    } catch (e: Exception) {
        null
    }

    companion object {
        private const val RESPONSE_KEY = "RESPONSE_KEY"
        const val REMOVED = "REMOVED"
        const val USER_EXISTS = "USER_EXISTS"
    }
}