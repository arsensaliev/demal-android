package com.demal.koin.modules

import com.demal.feature_profile.main.ProfileViewModel
import com.demal.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { ProfileViewModel(get(), get(), get()) }
}