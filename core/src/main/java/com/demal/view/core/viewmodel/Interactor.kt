package com.demal.view.core.viewmodel
import com.demal.model.data.user.BaseState
import com.demal.model.data.user.entity.AuthenticationResult

interface Interactor {
    suspend fun login(email: String, pass: String): BaseState<AuthenticationResult>
}