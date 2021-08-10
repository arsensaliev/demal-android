package com.demal.model.data.user

import com.demal.model.data.user.entity.AuthenticationResult

sealed class AppStateAuth {

    data class Success(val data: AuthenticationResult?) : AppStateAuth()
    data class Loading(val progress: Int?) : AppStateAuth()
    data class Error(val error: Throwable) : AppStateAuth()
}
