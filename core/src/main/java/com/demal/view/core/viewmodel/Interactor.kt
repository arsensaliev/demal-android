package com.demal.view.core.viewmodel
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.AuthenticationResult

interface Interactor {
    suspend fun login(email: String, pass: String): BaseState<AuthenticationResult>
}