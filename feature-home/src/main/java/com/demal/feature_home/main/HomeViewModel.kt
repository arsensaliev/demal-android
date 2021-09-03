package com.demal.feature_home.main

import com.demal.feature_home.navigation.HomeNavigator
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.tours.LikableTour
import com.demal.model.data.entity.tours.LikableTours
import com.demal.repository.interactor.ToursInteractor
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy
import com.demal.view.core.view_model.BaseViewModel

class HomeViewModel(
    private val navigator: HomeNavigator,
    private val toursInteractor: ToursInteractor,
) : BaseViewModel<LikableTours>(navigator) {

    private var tours = LikableTours(mutableListOf())

    fun getTours() {
        runAsync {
            mStateLiveData.postValue(BaseState.Loading(true))
            tours = toursInteractor.getTours(SortBy.CREATED_AT, Order.DESCENDING)
            mStateLiveData.value = BaseState.Success(tours)
        }
    }

    fun likePressed(tour: LikableTour) {
        runAsync {
            val pos = tours.toursList.indexOf(tour)
            val newTour = tour.copy(tour, !tour.isLiked)
            val newList = tours.toursList.toMutableList()
            newList[pos] = newTour
            tours = LikableTours(newList)
            mStateLiveData.postValue(BaseState.Success(tours))
        }
        runAsync {
            if (tour.isLiked) {
                toursInteractor.deleteFromWishList(tour.id)
            } else {
                toursInteractor.addToWishList(tour.id)
            }
        }
    }

    fun openTour(tour: LikableTour) {
        navigator.toTourScreen(tour.id)
    }

    override fun handleError(error: Throwable) {
        super.handleError(error)
        mStateLiveData.postValue(BaseState.Error(error))
    }
}