package com.demal.feature_wishlist.navigation

import com.demal.view.core.BaseNavigator

interface WishlistNavigator : BaseNavigator {
    fun toTourScreen(tourId: Int)
}