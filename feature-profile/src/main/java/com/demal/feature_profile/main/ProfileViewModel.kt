package com.demal.feature_profile.main

import android.util.Log
import com.demal.feature_profile.navigation.ProfileNavigator
import com.demal.model.data.entity.user.User
import com.demal.repository.repository.UserRepository
import com.demal.repository.interactor.ToursInteractor
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy
import com.demal.view.core.view_model.BaseViewModel

class ProfileViewModel(
    navigator: ProfileNavigator,
    private val repository: UserRepository,
    private val interactor: ToursInteractor
) : BaseViewModel<User>(navigator) {

    fun init() {
        //Для теста ошибки 401
        /*runAsync {
            val user = repository.login("arsen@mail.ru", "password")
            println(user.toString())
            val user2 = repository.myUser()
            println(user2.toString())
        }*/

        //For test
        runAsync {
            Log.d(TAG, "init: Hello World !!!")
            val user = repository.login("arsen@mail.ru", "password")
            Log.d(TAG, "init: Hello World !!!")
            val favoriteTours = interactor.getFavoriteTours()
            val favoriteTours1 = favoriteTours

            Log.d(TAG, "init: $favoriteTours")
        }
    }

    companion object {
        private const val TAG = "VM_PROFILE_TAG"
    }
}