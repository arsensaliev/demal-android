package com.demal.koin

import com.demal.main.view_model.MainViewModel
import com.demal.navigation.MainActivityNavigator
import com.demal.navigation.Navigator
import com.demal.navigation.Screens
import com.example.feat_profile.navigation.ProfileNavigator
import com.github.terrakok.cicerone.Cicerone
import org.koin.android.viewmodel.dsl.viewModel
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
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}