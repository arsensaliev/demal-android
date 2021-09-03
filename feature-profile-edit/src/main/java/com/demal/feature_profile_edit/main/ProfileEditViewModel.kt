package com.demal.feature_profile_edit.main

import com.demal.feature_profile_edit.navigation.ProfileEditNavigator
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.user.User
import com.demal.repository.repository.UserRepository
import com.demal.view.core.view_model.BaseViewModel

class ProfileEditViewModel(
    navigator: ProfileEditNavigator,
    private val repository: UserRepository
) : BaseViewModel<User>(navigator) {

    fun init() {
        runAsync {
            val user = repository.myUser()
            mStateLiveData.value = BaseState.Success(user)
        }
    }
}