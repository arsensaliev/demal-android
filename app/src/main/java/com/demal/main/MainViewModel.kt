package com.demal.main

import androidx.lifecycle.ViewModel
import com.demal.navigation.MainActivityNavigator
import com.demal.navigation.MainBottomNavigation

class MainViewModel(
    private val navigator: MainActivityNavigator
) : ViewModel(), MainBottomNavigation {

    //TODO: Delete when HomeFragment appears
    fun init() {
        navigator.toProfileScreen()
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

    override fun toProfileEditScreen() {
        navigator.toProfileEditScreen()
    }
}