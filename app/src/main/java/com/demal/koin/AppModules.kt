package com.demal.koin

import com.demal.navigation.Navigator
import com.demal.navigation.Screens
import com.github.terrakok.cicerone.Cicerone
import org.koin.dsl.module

val ciceroneModule = module {
    val cicerone = Cicerone.create()

    factory { cicerone.getNavigatorHolder() }

    factory { cicerone.router }

    single { Screens() }
}

val navigatorsModule = module {
    factory { Navigator(get(), get()) }
}