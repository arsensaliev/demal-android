package com.demal.navigation

import com.demal.feature_home.navigation.HomeCategoriesNavigator
import com.demal.feature_home.navigation.HomeNavigator
import com.demal.feature_home.navigation.HomeToursNavigator
import com.demal.feature_login.navigation.LoginNavigator
import com.demal.feature_profile.navigation.ProfileNavigator
import com.demal.feature_wishlist.navigation.WishlistNavigator
import com.demal.feature_profile_edit.navigation.ProfileEditNavigator
import com.demal.feature_tour.navigation.TourNavigator
import com.demal.feature_tours.navigation.ToursNavigator
import com.demal.view.core.BaseNavigator
import com.demal.view.core.NavigationContainer
import com.github.terrakok.cicerone.Router
import org.romeo.feature_register.RegisterNavigator

//Класс должен реализовать навигационные интерфейсы всех модулей
class Navigator(
    private val router: Router,
    private val screens: Screens
) : BaseNavigator, MainActivityNavigator, ProfileNavigator, ProfileEditNavigator, LoginNavigator,
    WishlistNavigator, HomeNavigator, MyToursNavigator, HomeCategoriesNavigator, HomeToursNavigator,
    ToursNavigator {

    override var navigationContainer: NavigationContainer? = null

    override fun toToursScreen(categoryId: Int) {
        router.navigateTo(screens.toursScreen(categoryId))
        navigationContainer?.showBottomNavigation()
    }

    override fun toProfileEditScreen() {
        router.navigateTo(screens.profileEditScreen())
        navigationContainer?.showBottomNavigation()
    }

    override fun toHomeScreen() {
        router.navigateTo(screens.homeScreen())
        navigationContainer?.showBottomNavigation()
    }

    override fun toToursScreen() {
        router.navigateTo(screens.toursScreen())
        navigationContainer?.showBottomNavigation()
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

    override fun toRegisterScreen() {
        router.navigateTo(screens.registerScreen())
        navigationContainer?.showBottomNavigation()
    }

    override fun toTourScreen(tourId: Int) {
        router.navigateTo(screens.tourScreen(tourId))
        navigationContainer?.showBottomNavigation()
    }

    override fun toLoginScreen() {
        router.navigateTo(screens.loginScreen())
        navigationContainer?.showBottomNavigation()
    }

    override fun onDestroyNavigation() {
        navigationContainer = null
    }

    override fun goBack() {
        router.exit()
        navigationContainer?.showBottomNavigation()
    }
}