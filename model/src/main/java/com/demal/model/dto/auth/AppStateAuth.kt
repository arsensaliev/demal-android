package com.demal.model.dto.auth

sealed class AppStateAuth {

    data class Success(val data: AuthenticationResultDto?) : AppStateAuth()
    data class Error(val error: Throwable) : AppStateAuth()
}
