package com.demal.repository.repository

interface TokenRepository {
    fun saveToken(token: String)
    fun getToken(): String?
    fun removeToken()
}