package com.demal.navigation

import com.demal.feature_home.main.view.HomeFragment
import com.demal.feature_login.main.LoginFragment
import com.demal.feature_profile.main.ProfileFragment
import com.demal.feature_profile_edit.main.ProfileEditFragment
import com.demal.feature_tour.main.TourFragment
import com.demal.feature_tours.main.ToursFragment
import com.demal.feature_wishlist.main.WishlistFragment
import com.demal.feature_profile_edit.main.ProfileEditFragment
import com.demal.view.MyToursFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import org.romeo.feature_register.RegisterFragment

//Все фрагменты должны быть объявленны здесь в виде FragmentScreen
class Screens {
    fun profileScreen() = FragmentScreen { ProfileFragment() }
    fun homeScreen() = FragmentScreen { HomeFragment() }
    fun profileEditScreen() = FragmentScreen { ProfileEditFragment() }
    fun loginScreen() = FragmentScreen { LoginFragment() }
    fun wishlistScreen() = FragmentScreen { WishlistFragment() }
    fun myToursScreen() = FragmentScreen { MyToursFragment() }
    fun tourScreen(tourId: Int) = FragmentScreen { TourFragment.newInstance(tourId) }
    fun toursScreen(categoryId: Int? = null) =
        FragmentScreen { ToursFragment.newInstance(categoryId) }

    fun registerScreen() = FragmentScreen { RegisterFragment() }
}