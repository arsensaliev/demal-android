package com.demal.main

import androidx.lifecycle.ViewModel
import com.demal.navigation.MainActivityNavigator
import com.demal.navigation.MainBottomNavigation

class MainViewModel(
    private val navigator: MainActivityNavigator
) : ViewModel(), MainBottomNavigation {

    fun init() {
        navigator.toProfileScreen() //TODO: Navigate to first fragment
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
}