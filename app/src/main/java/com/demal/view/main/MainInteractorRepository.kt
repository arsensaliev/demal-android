package com.demal.view.main

import com.demal.model.dto.auth.AppStateAuth
import com.demal.repository.Repository
import com.demal.view.core.viewmodel.Interactor


class MainInteractorRepository(
    private val repositoryRemote: Repository,
) : Interactor {

    override suspend fun getAuthData(email: String, pass: String): AppStateAuth {
        val appState: AppStateAuth
            appState = AppStateAuth.Success(repositoryRemote.getAuthData(email,pass))
        return appState
    }
}
