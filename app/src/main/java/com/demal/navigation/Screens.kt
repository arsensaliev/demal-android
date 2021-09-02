package com.demal.navigation

import com.demal.feature_login.main.LoginFragment
import com.demal.feature_profile.main.ProfileFragment
import com.demal.feature_wishlist.main.WishlistFragment
import com.demal.feature_profile_edit.main.ProfileEditFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

//Все фрагменты должны быть объявленны здесь в виде FragmentScreen
class Screens {
    fun profileScreen() = FragmentScreen { ProfileFragment() }
    fun profileEditScreen() = FragmentScreen { ProfileEditFragment() }
    fun loginScreen() = FragmentScreen { LoginFragment.newInstance()}
    fun wishlistScreen() = FragmentScreen { WishlistFragment() }
}