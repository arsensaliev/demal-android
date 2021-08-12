package com.demal.app

import androidx.multidex.MultiDexApplication
import com.demal.koin.modules.ciceroneModule
import com.demal.koin.modules.navigatorsModule
import com.demal.koin.modules.viewModelModule
import org.koin.core.context.startKoin

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                listOf(
                    viewModelModule,
                    ciceroneModule,
                    navigatorsModule
                )
            )
        }
    }
}