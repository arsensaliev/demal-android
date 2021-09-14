package com.demal.navigation

import com.demal.feature_home.navigation.HomeNavigator
import com.demal.feature_login.navigation.LoginNavigator
import com.demal.feature_profile.navigation.ProfileNavigator
import com.demal.feature_wishlist.navigation.WishlistNavigator
import com.demal.feature_profile_edit.navigation.ProfileEditNavigator
import com.demal.view.core.BaseNavigator
import com.demal.view.core.NavigationContainer
import com.github.terrakok.cicerone.Router

//Класс должен реализовать навигационные интерфейсы всех модулей
class Navigator(
    private val router: Router,
    private val screens: Screens,
) : BaseNavigator, MainActivityNavigator, ProfileNavigator, ProfileEditNavigator, LoginNavigator,
    WishlistNavigator, HomeNavigator, MyToursNavigator {

    override var navigationContainer: NavigationContainer? = null

    override fun toHomeScreen() {
        router.navigateTo(screens.homeScreen())
        navigationContainer?.showBottomNavigation()
    }

    override fun toToursScreen() {
        //TODO("Not yet implemented")
    }

    override fun toMyToursScreen() {
        router.navigateTo(screens.myToursScreen())
        navigationContainer?.showBottomNavigation()
    }

    override fun toWishlistScreen() {
        router.navigateTo(screens.wishlistScreen())
        navigationContainer?.showBottomNavigation()
    }

    override fun toProfileScreen() {
        router.navigateTo(screens.profileScreen())
        navigationContainer?.showBottomNavigation()
    }

    override fun toTourScreen(tourId: Int) {
        //TODO("Not yet implemented")
    }

    //TODO: Navigate to Login Screen
    override fun toLoginScreen() {
        router.navigateTo(screens.loginScreen())
        navigationContainer?.hideBottomNavigation()
    }

    override fun onDestroyNavigation() {
        navigationContainer = null
    }
}