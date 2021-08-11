package com.demal.koin.modules

import com.demal.feature_profile.main.ProfileViewModel
import com.demal.main.MainViewModel
import com.demal.navigation.MainActivityNavigator
import com.demal.navigation.Navigator
import com.demal.navigation.Screens
import com.demal.feature_profile.navigation.ProfileNavigator
import com.demal.repository.api.ApiService
import com.demal.repository.api.BaseInterceptor
import com.demal.repository.data_sources.RemoteDataSource
import com.demal.repository.data_sources.RemoteDataSourceImpl
import com.demal.repository.repository.UserRepository
import com.demal.repository.repository.UserRepositoryImpl
import com.demal.view.core.BaseNavigator
import com.github.terrakok.cicerone.Cicerone
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
    factory<BaseNavigator> { get<Navigator>() }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { ProfileViewModel(get(), get()) }
}

val retrofitModule = module {
    single {
        val client = OkHttpClient.Builder()
            .addInterceptor(BaseInterceptor.interceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL_LOCATIONS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build().create(ApiService::class.java)
    }
}

val dataSourceModule = module {
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
}

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
}

const val BASE_URL_LOCATIONS = "https://demal-api.herokuapp.com/api/v1/"