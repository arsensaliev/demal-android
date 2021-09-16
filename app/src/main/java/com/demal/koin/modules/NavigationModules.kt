package com.demal.koin.modules

import com.demal.feature_home.navigation.HomeCategoriesNavigator
import com.demal.feature_home.navigation.HomeToursNavigator
import com.demal.feature_login.navigation.LoginNavigator
import com.demal.feature_profile.navigation.ProfileNavigator
import com.demal.feature_wishlist.navigation.WishlistNavigator
import com.demal.feature_profile_edit.navigation.ProfileEditNavigator
import com.demal.navigation.MainActivityNavigator
import com.demal.navigation.MyToursNavigator
import com.demal.navigation.Navigator
import com.demal.navigation.Screens
import com.demal.view.core.BaseNavigator
import com.github.terrakok.cicerone.Cicerone
import org.koin.dsl.module
import org.romeo.feature_registration.RegistrationNavigator

val ciceroneModule = module {
    val cicerone = Cicerone.create()

    factory { cicerone.getNavigatorHolder() }

    factory { cicerone.router }

    single { Screens() }
}

val navigatorsModule = module {
    single { Navigator(get(), get()) }

    factory<MainActivityNavigator> { get<Navigator>() }
    factory<ProfileNavigator> { get<Navigator>() }
    factory<HomeToursNavigator> { get<Navigator>() }
    factory<HomeCategoriesNavigator> { get<Navigator>() }
    factory<ProfileEditNavigator> { get<Navigator>() }
    factory<BaseNavigator> { get<Navigator>() }
    factory<LoginNavigator>{ get<Navigator>() }
    factory<WishlistNavigator> { get<Navigator>() }
    factory<MyToursNavigator> { get<Navigator>() }
    factory<RegistrationNavigator> { get<Navigator>() }
}