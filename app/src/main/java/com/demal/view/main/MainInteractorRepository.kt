package com.demal.view.main

import com.demal.model.data.user.AppStateAuth
import com.demal.repository.Repository
import com.demal.view.core.viewmodel.Interactor


class MainInteractorRepository(
    private val repositoryRemote: Repository,
) : Interactor {

    override suspend fun login(email: String, pass: String): AppStateAuth {
        val appState: AppStateAuth
        appState = AppStateAuth.Success(repositoryRemote.login(email, pass))
        return appState
    }
}
