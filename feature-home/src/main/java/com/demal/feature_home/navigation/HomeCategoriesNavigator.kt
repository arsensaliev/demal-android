package com.demal.feature_home.navigation

import com.demal.view.core.BaseNavigator

interface HomeCategoriesNavigator : BaseNavigator {
    fun toToursScreen(categoryId: Int)
}