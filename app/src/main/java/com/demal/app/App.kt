package com.demal.app

import androidx.multidex.MultiDexApplication
import com.demal.koin.ciceroneModule
import com.demal.koin.navigatorsModule
import com.demal.koin.viewModelModule
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