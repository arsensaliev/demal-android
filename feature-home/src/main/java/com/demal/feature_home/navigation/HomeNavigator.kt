package com.demal.feature_home.navigation

import com.demal.view.core.BaseNavigator

interface HomeNavigator : BaseNavigator {
    fun toTourScreen(tourId: Int)
}