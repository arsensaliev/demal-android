package com.demal.navigation

import com.demal.view.core.BaseNavigator

interface MainActivityNavigator : BaseNavigator, MainBottomNavigation {
    fun toProfileEditScreen()
}