package com.demal.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demal.navigation.MainActivityNavigator
import com.demal.navigation.MainBottomNavigation
import com.demal.repository.repository.UserRepositoryLocal
import com.demal.view.core.NavigationContainer

class MainViewModel(
    private val navigator: MainActivityNavigator,
    private val userRepositoryLocal: UserRepositoryLocal
) : ViewModel(), MainBottomNavigation, NavigationContainer {

    private val mShowBottomNavigationLiveData = MutableLiveData(true)

    fun getBottomNavigationLiveData() = mShowBottomNavigationLiveData as LiveData<Boolean>

    fun init() {
        navigator.navigationContainer = this

        if (userRepositoryLocal.isFirstLaunch())
            navigator.toOnboardingScreen()
        else navigator.toHomeScreen()
    }

    override fun toHomeScreen() {
        navigator.toHomeScreen()
    }

    override fun toToursScreen() {
        navigator.toToursScreen()
    }

    override fun toMyToursScreen() {
        navigator.toMyToursScreen()
    }

    override fun toWishlistScreen() {
        navigator.toWishlistScreen()
    }

    override fun toProfileScreen() {
        navigator.toProfileScreen()
    }

    override fun onCleared() {
        super.onCleared()
        navigator.onDestroyNavigation()
    }

    override fun hideBottomNavigation() {
        mShowBottomNavigationLiveData.postValue(false)
    }

    override fun showBottomNavigation() {
        mShowBottomNavigationLiveData.postValue(true)
    }
}