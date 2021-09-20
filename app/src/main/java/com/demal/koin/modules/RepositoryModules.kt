package com.demal.koin.modules

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.widget.ImageView
import com.demal.repository.data_sources.RemoteDataSource
import com.demal.repository.data_sources.RemoteDataSourceImpl
import com.demal.repository.data_sources.WishlistDataSourceLocal
import com.demal.repository.data_sources.WishlistDataSourceLocalImpl
import com.demal.repository.data_sources.preferences.GeneralPreferencesDataSource
import com.demal.repository.data_sources.preferences.ParcelablePreferencesDataSource
import com.demal.repository.data_sources.preferences.ParcelablePreferencesDataSourceImpl
import com.demal.repository.data_sources.preferences.PreferencesDataSourceImpl
import com.demal.repository.image.GlideImageLoader
import com.demal.repository.image.ImageLoader
import com.demal.repository.interactor.ToursInteractor
import com.demal.repository.interactor.ToursInteractorImpl
import com.demal.repository.repository.*
import com.demal.utils.files.ContentFileUtil
import org.koin.dsl.module

val dataSourceModule = module {
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
    single<WishlistDataSourceLocal> { WishlistDataSourceLocalImpl() }
}

val preferencesModule = module {
    single<ParcelablePreferencesDataSource> { ParcelablePreferencesDataSourceImpl(get()) }
    single<SharedPreferences> { get<Context>().getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE) }
    single<GeneralPreferencesDataSource> { PreferencesDataSourceImpl(get(), get()) }
}

val repositoryModule = module {
    single<UserRepositoryLocal> { UserRepositoryLocalImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get(), get()) }
    single<ToursRepository> { ToursRepositoryImpl(get(), get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
}

val interactorModule = module {
    single<ToursInteractor> {
        ToursInteractorImpl(get(), get())
    }
}

val imageModule = module {
    single<ImageLoader<ImageView>> { GlideImageLoader() }
}

val contentFileUtil = module {
    single{ ContentFileUtil() }
}

private const val APP_PREFERENCES = "APP_PREFERENCES"