package com.demal.navigation

import com.demal.model.data.entity.tours.LikableTour
import com.demal.view.core.BaseNavigator

interface MyToursNavigator: BaseNavigator {
    fun toTourScreen(tour: LikableTour)
}