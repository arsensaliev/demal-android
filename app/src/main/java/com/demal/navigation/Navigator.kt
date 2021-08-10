package com.demal.navigation

import com.example.feat_profile.navigation.ProfileNavigator
import com.github.terrakok.cicerone.Router

//Класс должен реализовать навигационные интерфейсы всех модулей
class Navigator(
    private val router: Router,
    private val screens: Screens
) : MainActivityNavigator, ProfileNavigator {

    override fun toProfileScreen() = router.navigateTo(screens.profileScreen())
}