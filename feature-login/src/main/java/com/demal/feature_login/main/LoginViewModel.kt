package com.demal.feature_login.main

import com.demal.feature_login.navigation.LoginNavigator
import com.demal.model.data.entity.login.LoginStatus
import com.demal.repository.repository.UserRepository
import com.demal.view.core.view_model.BaseViewModel

class LoginViewModel(
    navigator: LoginNavigator,
    private val repository: UserRepository
) : BaseViewModel<LoginStatus>(navigator)