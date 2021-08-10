package com.demal.view.core.viewmodel
import com.demal.model.data.user.AppStateAuth

interface Interactor {
    suspend fun login(email: String, pass: String): AppStateAuth
}