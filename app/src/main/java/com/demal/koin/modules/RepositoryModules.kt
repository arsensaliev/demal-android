package com.demal.koin.modules

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.widget.ImageView
import com.demal.repository.data_sources.RemoteDataSource
import com.demal.repository.data_sources.RemoteDataSourceImpl
import com.demal.repository.data_sources.preferences.GeneralPreferencesDataSource
import com.demal.repository.data_sources.preferences.ParcelablePreferencesDataSource
import com.demal.repository.data_sources.preferences.ParcelablePreferencesDataSourceImpl
import com.demal.repository.data_sources.preferences.PreferencesDataSourceImpl
import com.demal.repository.image.GlideImageLoader
import com.demal.repository.image.ImageLoader
import com.demal.repository.intractor.ToursInteractor
import com.demal.repository.intractor.ToursInteractorImpl
import com.demal.repository.repository.*
import org.koin.dsl.module

val dataSourceModule = module {
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
}

val preferencesModule = module {
    single<ParcelablePreferencesDataSource> { ParcelablePreferencesDataSourceImpl(get()) }
    single<SharedPreferences> {get<Context>().getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)}
    single<GeneralPreferencesDataSource> { PreferencesDataSourceImpl(get(), get()) }
}

val repositoryModule = module {
    single<LoginResponseRepositoryLocal> { LoginResponseRepositoryLocalImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get(), get()) }
    single<ToursRepository> { ToursRepositoryImpl(get()) }
}

val interactorModule = module {
    single<ToursInteractor> {
        ToursInteractorImpl(get(), get())
    }
}

val imageModule = module {
    single<ImageLoader<ImageView>> { GlideImageLoader() }
}

private const val APP_PREFERENCES = "APP_PREFERENCES"