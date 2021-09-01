package com.demal.navigation

import com.demal.feature_profile.main.ProfileFragment
import com.demal.feature_profile_edit.main.ProfileEditFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

//Все фрагменты должны быть объявленны здесь в виде FragmentScreen
class Screens {
    fun profileScreen() = FragmentScreen { ProfileEditFragment() }
    fun profileEditScreen() = FragmentScreen { ProfileEditFragment() }
}