package com.demal.navigation

import com.demal.feature_profile.navigation.ProfileNavigator
import com.github.terrakok.cicerone.Router

//Класс должен реализовать навигационные интерфейсы всех модулей
class Navigator(
    private val router: Router,
    private val screens: Screens
) : MainActivityNavigator, ProfileNavigator {

    override fun toHomeScreen() {
        //TODO("Not yet implemented")
    }

    override fun toToursScreen() {
        //TODO("Not yet implemented")
    }

    override fun toMyToursScreen() {
        //TODO("Not yet implemented")
    }

    override fun toWishlistScreen() {
        //TODO("Not yet implemented")
    }

    override fun toProfileScreen() = router.navigateTo(screens.profileScreen())
}