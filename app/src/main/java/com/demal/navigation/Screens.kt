package com.demal.navigation

import com.demal.feature_profile.main.ProfileFragment
import com.demal.feature_tours.main.ToursFragment
import com.demal.feature_wishlist.main.WishlistFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

//Все фрагменты должны быть объявленны здесь в виде FragmentScreen
class Screens {
    fun profileScreen() = FragmentScreen { ProfileFragment() }
    fun wishlistScreen() = FragmentScreen { WishlistFragment() }
    fun toursScreen() = FragmentScreen { ToursFragment() }
}