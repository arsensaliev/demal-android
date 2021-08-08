package com.demal

import androidx.multidex.MultiDexApplication
import com.demal.koin.ciceroneModule
import com.demal.koin.navigatorsModule
import org.koin.core.context.startKoin

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                ciceroneModule,
                navigatorsModule
            )
        }
    }
}