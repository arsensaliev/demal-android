package com.demal.koin

import org.koin.dsl.module
import ru.terrakok.cicerone.Cicerone

val ciceroneModule = module {
    val cicerone = Cicerone.create()

    factory { cicerone.navigatorHolder }

    factory { cicerone.router }
}

val navigatorsModule = module {

}