package com.demal.navigation

import com.demal.feature_login.main.LoginFragment
import com.demal.feature_profile.main.ProfileFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

//Все фрагменты должны быть объявленны здесь в виде FragmentScreen
class Screens {
    fun profileScreen() = FragmentScreen { ProfileFragment() }
    fun loginScreen() = FragmentScreen { LoginFragment.newInstance()}
}