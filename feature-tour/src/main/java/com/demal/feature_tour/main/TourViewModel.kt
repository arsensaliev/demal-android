package com.demal.feature_tour.main

import com.demal.feature_tour.navigation.TourNavigator
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.tours.LikableTour
import com.demal.repository.interactor.ToursInteractor
import com.demal.view.core.view_model.BaseViewModel

class TourViewModel(
    private val navigator: TourNavigator,
    private val toursInteractor: ToursInteractor
) : BaseViewModel<LikableTour>(navigator) {

    private lateinit var tour: LikableTour

    fun getTour(tourId: Int) {
        runAsync {
            tour = toursInteractor.getTourById(tourId)
            postTour()
        }
    }

    fun likePressed() {
        runAsync {
            if (tour.isLiked) {
                toursInteractor.deleteFromWishList(tour.id)
            } else {
                toursInteractor.addToWishList(tour.id)
            }
        }
        runAsync {
            tour = tour.copy(tour, !tour.isLiked)
            postTour()
        }
    }

    fun closeTour() {
        navigator.goBack()
    }

    override fun handleError(error: Throwable) {
        super.handleError(error)
        mStateLiveData.postValue(BaseState.Error(error))
    }

    private fun postTour() {
        mStateLiveData.postValue(BaseState.Success(tour))
    }
}