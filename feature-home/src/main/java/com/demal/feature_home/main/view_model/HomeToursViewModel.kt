package com.demal.feature_home.main.view_model

import com.demal.feature_home.navigation.HomeToursNavigator
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.tours.LikableTour
import com.demal.model.data.entity.tours.LikableTours
import com.demal.repository.interactor.ToursInteractor
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy
import com.demal.view.core.view_model.BaseViewModel

class HomeToursViewModel(
    private val navigator: HomeToursNavigator,
    private val toursInteractor: ToursInteractor
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
            val newList = tours.toursList.toMutableList()
            newList[tours.toursList.indexOf(tour)] =  LikableTour(tour, !tour.isLiked)
            tours = LikableTours(newList)
            mStateLiveData.postValue(BaseState.Success(tours))

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