package com.demal.navigation

import com.demal.feature_profile.navigation.ProfileNavigator
import com.demal.view.core.BaseNavigator
import com.github.terrakok.cicerone.Router

//Класс должен реализовать навигационные интерфейсы всех модулей
class Navigator(
    private val router: Router,
    private val screens: Screens
) : BaseNavigator, MainActivityNavigator, ProfileNavigator {

    override fun toProfileScreen() = router.navigateTo(screens.profileScreen())

    //TODO: Navigate to Login Screen
    override fun toLoginScreen() = router.navigateTo(screens.profileScreen())
}