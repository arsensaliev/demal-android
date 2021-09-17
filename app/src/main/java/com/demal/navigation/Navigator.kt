package com.demal.navigation

import com.demal.feature_home.navigation.HomeCategoriesNavigator
import com.demal.feature_home.navigation.HomeToursNavigator
import com.demal.feature_login.navigation.LoginNavigator
import com.demal.feature_profile.navigation.ProfileNavigator
import com.demal.feature_wishlist.navigation.WishlistNavigator
import com.demal.feature_profile_edit.navigation.ProfileEditNavigator
import com.demal.feature_wishlist.navigation.WishlistNavigator
import com.demal.view.core.BaseNavigator
import com.github.terrakok.cicerone.Router

//Класс должен реализовать навигационные интерфейсы всех модулей
class Navigator(
    private val router: Router,
    private val screens: Screens
) : BaseNavigator, MainActivityNavigator, ProfileNavigator, ProfileEditNavigator, LoginNavigator,
    WishlistNavigator, HomeToursNavigator, HomeCategoriesNavigator,  MyToursNavigator {

    override fun toHomeScreen() = router.navigateTo(screens.homeScreen())

    override fun toToursScreen() {
        //TODO("Not yet implemented")
    }

    override fun toMyToursScreen() = router.navigateTo(screens.myToursScreen())

    override fun toWishlistScreen() = router.navigateTo(screens.wishlistScreen())

    override fun toProfileScreen() = router.navigateTo(screens.profileScreen())

    override fun toProfileEditScreen() = router.navigateTo(screens.profileEditScreen())

    override fun toTourScreen(tourId: Int) {
        //TODO("Not yet implemented")
    }

    override fun toToursScreen(categoryId: Int) {
        //TODO("Not yet implemented")
    }

    //TODO: Navigate to Login Screen
    override fun toLoginScreen() = router.navigateTo(screens.loginScreen())

    override fun back() = router.exit()
}