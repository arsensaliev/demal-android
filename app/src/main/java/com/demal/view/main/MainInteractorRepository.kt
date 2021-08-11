package com.demal.view.main

import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.AuthenticationResult
import com.demal.repository.Repository
import com.demal.view.core.viewmodel.Interactor


class MainInteractorRepository(
    private val repositoryRemote: Repository,
) : Interactor {

    override suspend fun login(email: String, pass: String): BaseState<AuthenticationResult> {
        val baseState: BaseState<AuthenticationResult>
        baseState = BaseState.Success(repositoryRemote.login(email, pass))
        return baseState
    }
}
