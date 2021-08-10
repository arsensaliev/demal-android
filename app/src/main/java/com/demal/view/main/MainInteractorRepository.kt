package com.demal.view.main

import com.demal.model.data.user.AppState
import com.demal.model.data.user.entity.AuthenticationResult
import com.demal.repository.Repository
import com.demal.view.core.viewmodel.Interactor


class MainInteractorRepository(
    private val repositoryRemote: Repository,
) : Interactor {

    override suspend fun login(email: String, pass: String): AppState<AuthenticationResult> {
        val appState: AppState<AuthenticationResult>
        appState = AppState.Success(repositoryRemote.login(email, pass))
        return appState
    }
}
