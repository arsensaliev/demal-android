package com.demal.koin.modules

import com.demal.feature_home.navigation.HomeNavigator
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
    factory<HomeNavigator> { get<Navigator>() }
    factory<ProfileEditNavigator> { get<Navigator>() }
    factory<BaseNavigator> { get<Navigator>() }
    factory<LoginNavigator>{ get<Navigator>() }
    factory<WishlistNavigator> { get<Navigator>() }
    factory<MyToursNavigator> { get<Navigator>() }

}