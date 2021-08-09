package com.demal.view.core.viewmodel
import com.demal.model.dto.auth.AppStateAuth

interface Interactor {
    suspend fun getAuthData(email: String,pass: String): AppStateAuth
}