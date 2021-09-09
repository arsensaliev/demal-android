package com.demal.feature_profile.main

import com.demal.feature_profile.navigation.ProfileNavigator
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.user.User
import com.demal.repository.repository.UserRepository
import com.demal.view.core.view_model.BaseViewModel

class ProfileViewModel(
    val navigator: ProfileNavigator,
    private val repository: UserRepository
) : BaseViewModel<User>(navigator) {

    fun init() {
        runAsync {
            val user = repository.login("arsen@mail.ru", "password")
            mStateLiveData.value = BaseState.Success(user)
        }
    }
}