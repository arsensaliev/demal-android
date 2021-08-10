package com.demal.di

import com.demal.MainActivity
import com.demal.repository.Repository
import com.demal.repository.RetrofitImplementation
import com.demal.repository.api.BaseInterceptor
import com.demal.view.main.AuthViewModel
import com.demal.view.main.MainInteractorRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun injectDependencies() = loadModules

private val loadModules by lazy {
    loadKoinModules(listOf(application, mainScreen,okHttpClientModule,retrofitModule))
}

val application = module {
    single<Repository> { RetrofitImplementation(get()) }
}

val mainScreen = module {
    scope(named<MainActivity>()) {
        scoped { MainInteractorRepository(get()) }
        viewModel { AuthViewModel(get()) }
    }
}

val okHttpClientModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(BaseInterceptor.interceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }
}

val retrofitModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(RetrofitImplementation.BASE_URL_LOCATIONS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(get())
            .build()
    }


}
