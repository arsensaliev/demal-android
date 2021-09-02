package com.demal.navigation

import com.demal.feature_home.navigation.HomeNavigator
import com.demal.feature_profile.navigation.ProfileNavigator
import com.demal.feature_profile_edit.navigation.ProfileEditNavigator
import com.demal.view.core.BaseNavigator
import com.github.terrakok.cicerone.Router

//Класс должен реализовать навигационные интерфейсы всех модулей
class Navigator(
    private val router: Router,
    private val screens: Screens
) : BaseNavigator, MainActivityNavigator, ProfileNavigator, ProfileEditNavigator, HomeNavigator {

    override fun toHomeScreen() = router.navigateTo(screens.homeScreen())

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

    //TODO: Navigate to Login Screen
    override fun toLoginScreen() = router.navigateTo(screens.profileScreen())
}