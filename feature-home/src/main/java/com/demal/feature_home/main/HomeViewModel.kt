package com.demal.feature_home.main

import com.demal.feature_home.navigation.HomeNavigator
import com.demal.model.data.entity.tours.Tours
import com.demal.repository.interactor.ToursInteractor
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy
import com.demal.view.core.view_model.BaseViewModel

class HomeViewModel(
    navigator: HomeNavigator,
    private val toursInteractor: ToursInteractor
) : BaseViewModel<Tours>(navigator) {

    // test
    fun init() {
        runAsyncWithResult {
            val tours = toursInteractor.getTours(SortBy.CREATED_AT, Order.DESCENDING)
            println(tours.toString())
        }
    }
}