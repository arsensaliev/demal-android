package com.demal.koin.modules

import com.demal.feature_home.main.HomeViewModel
import com.demal.feature_login.main.LoginViewModel
import com.demal.feature_profile.main.ProfileViewModel
import com.demal.feature_wishlist.main.WishlistViewModel
import com.demal.feature_profile_edit.main.ProfileEditViewModel
import com.demal.main.MainViewModel
import com.demal.view_model.MyToursViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { ProfileViewModel(get(), get()) }
    viewModel { WishlistViewModel(get(), get()) }
    viewModel { ProfileEditViewModel(get(), get(),get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { MyToursViewModel(get(),get()) }

}