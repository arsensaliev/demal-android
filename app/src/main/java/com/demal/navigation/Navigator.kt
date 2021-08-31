package com.demal.navigation

import com.demal.feature_profile.navigation.ProfileNavigator
import com.demal.feature_tours.navigation.ToursNavigator
import com.demal.feature_wishlist.navigation.WishlistNavigator
import com.demal.view.core.BaseNavigator
import com.github.terrakok.cicerone.Router

//Класс должен реализовать навигационные интерфейсы всех модулей
class Navigator(
    private val router: Router,
    private val screens: Screens
) : BaseNavigator, MainActivityNavigator, ProfileNavigator, WishlistNavigator, ToursNavigator {

    override fun toHomeScreen() {
        //TODO("Not yet implemented")
    }

    override fun toToursScreen() = router.navigateTo(screens.toursScreen())

    override fun toMyToursScreen() {
        //TODO("Not yet implemented")
    }

    override fun toWishlistScreen() = router.navigateTo(screens.wishlistScreen())

    override fun toProfileScreen() = router.navigateTo(screens.profileScreen())

    //TODO: Navigate to Login Screen
    override fun toLoginScreen() = router.navigateTo(screens.profileScreen())
}