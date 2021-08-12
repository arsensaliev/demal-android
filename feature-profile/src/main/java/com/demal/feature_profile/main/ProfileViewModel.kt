package com.demal.feature_profile.main

import com.demal.feature_profile.navigation.ProfileNavigator
import com.demal.model.data.entity.user.User
import com.demal.repository.repository.UserRepository
import com.demal.view.core.view_model.BaseViewModel

class ProfileViewModel(
    navigator: ProfileNavigator,
    private val repository: UserRepository
) : BaseViewModel<User>(navigator) {

    fun init() {
        //Для теста ошибки 401
        runAsync {
            repository.myUser()
        }
    }
}