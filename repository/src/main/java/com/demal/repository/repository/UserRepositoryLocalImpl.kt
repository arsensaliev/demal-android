package com.demal.repository.repository

import com.demal.model.data.entity.user.LoginResponse
import com.demal.repository.data_sources.preferences.GeneralPreferencesDataSource

class UserRepositoryLocalImpl(
    private val preferencesDataSource: GeneralPreferencesDataSource
) : UserRepositoryLocal {

    private var isFirstLaunch = false

    override suspend fun saveResponse(response: LoginResponse) {
        preferencesDataSource.putParcelable(RESPONSE_KEY, response)
    }

    override fun getUserToken() =
        getResponse()?.auth?.token

    override suspend fun removeUser() {
        preferencesDataSource.remove(RESPONSE_KEY)
    }

    override fun isFirstLaunch(): Boolean {
        val isFirstLaunch = preferencesDataSource.getBoolean(IS_FIRST_LAUNCH) ?: true

        if (isFirstLaunch)
            preferencesDataSource.putBoolean(IS_FIRST_LAUNCH, false)

        return isFirstLaunch
    }

    override fun getUser() =
        getResponse()?.user

    private fun getResponse() =
        preferencesDataSource.getParcelable(RESPONSE_KEY, LoginResponse::class.java)

    companion object {
        private const val RESPONSE_KEY = "RESPONSE_KEY"
        private const val IS_FIRST_LAUNCH = "IS_FIRST_LAUNCH"
    }
}