package com.demal.koin.modules

import com.demal.main.MainViewModel
import com.demal.navigation.MainActivityNavigator
import com.demal.navigation.Navigator
import com.demal.navigation.Screens
import com.demal.feature_profile.navigation.ProfileNavigator
import com.demal.repository.RetrofitImplementation
import com.demal.repository.api.BaseInterceptor
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
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val retrofitModule = module {
    single {
        val client = OkHttpClient.Builder()
            .addInterceptor(BaseInterceptor.interceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        Retrofit.Builder()
            .baseUrl(RetrofitImplementation.BASE_URL_LOCATIONS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }
}