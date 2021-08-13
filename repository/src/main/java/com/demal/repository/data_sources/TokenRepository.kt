package com.demal.repository.data_sources

interface TokenRepository {
    fun saveToken(token: String)
    fun getToken(): String?
    fun removeToken()
}