package com.demal.feature_login.main

import android.annotation.SuppressLint
import com.demal.feature_login.navigation.LoginNavigator
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.login.LoginStatus
import com.demal.model.data.exceptions.BadRequestException
import com.demal.model.data.exceptions.NoAuthException
import com.demal.repository.repository.UserRepository
import com.demal.repository.repository.UserRepositoryLocal
import com.demal.view.core.view_model.BaseViewModel

class LoginViewModel(
    val navigator: LoginNavigator,
    private val repository: UserRepository,
    private val repositoryLocal: UserRepositoryLocal
) : BaseViewModel<LoginStatus>(navigator){

    init {
        runAsync {
            repositoryLocal.removeUser()
        }
    }

    fun login(
        email: String,
        password: String
    ) = runAsync {
        repository.login(email, password)
        mStateLiveData.postValue(BaseState.Success(LoginStatus()))
    }

    fun navigateToRegisterScreen() {
        navigator.toRegisterScreen()
    }

    @SuppressLint("MissingSuperCall")
    override fun handleError(error: Throwable) {
        if (error is NoAuthException || error is BadRequestException) {
            mStateLiveData.postValue(BaseState.Error(error))
        }
    }
}