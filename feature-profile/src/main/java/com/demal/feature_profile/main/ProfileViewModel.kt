package com.demal.feature_profile.main

import com.demal.feature_profile.navigation.ProfileNavigator
import com.demal.model.data.entity.user.User
import com.demal.repository.data_sources.TokenRepository
import com.demal.repository.repository.UserRepository
import com.demal.view.core.view_model.BaseViewModel

class ProfileViewModel(
    navigator: ProfileNavigator,
    tokenRepository: TokenRepository,
    private val repository: UserRepository
) : BaseViewModel<User>(navigator, tokenRepository) {

    fun init() {
        //Для теста ошибки 401
        runAsync {
            val user = repository.login("arsen@mail.ru", "password")
            println(user.toString())
            val user2 = repository.myUser()
            println(user2.toString())
        }
    }
}