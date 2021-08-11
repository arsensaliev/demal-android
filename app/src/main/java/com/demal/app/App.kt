package com.demal.app

import androidx.multidex.MultiDexApplication
import com.demal.koin.modules.ciceroneModule
import com.demal.koin.modules.navigatorsModule
import com.demal.koin.modules.retrofitModule
import com.demal.koin.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)

            modules(
                listOf(
                    viewModelModule,
                    ciceroneModule,
                    navigatorsModule,
                    retrofitModule
                )
            )
        }
    }
}