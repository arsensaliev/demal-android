package com.demal

import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.tours.LikableTour
import com.demal.repository.interactor.ToursInteractor
import com.demal.view.core.view_model.BaseViewModel

class TourViewModel(
    private val navigator: TourNavigator,
    private val repository: ToursInteractor
) : BaseViewModel<LikableTour>(navigator) {

    private lateinit var tour : LikableTour

    fun getTourDescription(tourId: Int) {
        runAsync {
            mStateLiveData.postValue(BaseState.Loading(true))
            tour = repository.getTourById(id = tourId)
            mStateLiveData.value = BaseState.Success(tour)
        }
    }

    fun likePressed(tour: LikableTour) {
        runAsync {
            if (tour.isLiked){
                repository.deleteFromWishList(tour.id)
            } else {
                repository.addToWishList(tour.id)
            }
            getTourDescription(tour.id)
        }
    }

    fun closeTour(){
        navigator.toToursScreen()
    }

    override fun handleError(error: Throwable) {
        super.handleError(error)
        mStateLiveData.postValue(BaseState.Error(error))
    }


}