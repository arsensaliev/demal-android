package com.demal.repository.data_sources

import com.demal.repository.data_sources.preferences.GeneralPreferencesDataSource

class TokenRepositoryImpl(
    private val preferencesDataSource: GeneralPreferencesDataSource
) : TokenRepository {

    override fun saveToken(token: String) {
        preferencesDataSource.putString(TOKEN_KEY, token)
    }

    override fun getToken() =
        preferencesDataSource.getString(TOKEN_KEY)

    override fun removeToken() {
        preferencesDataSource.remove(TOKEN_KEY)
    }

    companion object {
        private const val TOKEN_KEY = "TOKEN_KEY"
    }
}