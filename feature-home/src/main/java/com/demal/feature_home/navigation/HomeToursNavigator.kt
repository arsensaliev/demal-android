package com.demal.feature_home.navigation

import com.demal.view.core.BaseNavigator

interface HomeToursNavigator : BaseNavigator {
    fun toTourScreen(tourId: Int)
}