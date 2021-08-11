package com.demal.view.main

import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.user.LoginResponse
import com.demal.repository.Repository
import com.demal.view.core.view_model.Interactor


class MainInteractorRepository(
    private val repositoryRemote: Repository,
) : Interactor {

    override suspend fun login(email: String, pass: String): BaseState<LoginResponse> {
        val baseState: BaseState<LoginResponse>
        baseState = BaseState.Success(repositoryRemote.login(email, pass))
        return baseState
    }
}
