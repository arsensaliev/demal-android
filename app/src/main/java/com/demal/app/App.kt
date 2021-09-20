package com.demal.app

import androidx.multidex.MultiDexApplication
import com.demal.koin.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                viewModelModule,
                ciceroneModule,
                navigatorsModule,
                retrofitModule,
                dataSourceModule,
                repositoryModule,
                interactorModule,
                imageModule,
                preferencesModule,
                contentFileUtil
            )
        }
    }
}