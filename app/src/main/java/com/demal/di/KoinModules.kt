package com.demal.di

import com.demal.MainActivity
import com.demal.repository.Repository
import com.demal.repository.RetrofitImplementation
import com.demal.view.main.AuthViewModel
import com.demal.view.main.MainInteractorRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun injectDependencies() = loadModules

private val loadModules by lazy {
    loadKoinModules(listOf(application, mainScreen))
}

val application = module {
    single<Repository> { RetrofitImplementation() }
}

val mainScreen = module {
    scope(named<MainActivity>()) {
        scoped { MainInteractorRepository(get()) }
        viewModel { AuthViewModel(get()) }
    }
}
