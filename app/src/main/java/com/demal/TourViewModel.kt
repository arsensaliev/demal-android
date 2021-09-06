package com.demal

import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.tours.LikableTour
import com.demal.repository.interactor.ToursInteractor
import com.demal.view.core.view_model.BaseViewModel

class TourViewModel(
    private val navigator: TourNavigator,
    private val toursInteractor: ToursInteractor
) : BaseViewModel<LikableTour>(navigator) {

    private lateinit var tour : LikableTour

    fun getTourDescription(tourId: Int) {
        runAsync {
            mStateLiveData.postValue(BaseState.Loading(true))
            tour = toursInteractor.getTourById(id = tourId)
            mStateLiveData.value = BaseState.Success(tour)
        }
    }

    fun getTourImages(tourId: Int): List<Int> {
        //Надо написать загрузку картинок, не могу, прошу помочь.
        return listOf(
            R.drawable.tour_background_image,
            R.drawable.profile_bg_bitmap,
            R.drawable.profile_bg_dark
        )
    }

    fun likePressed(tour: LikableTour) {
        runAsync {
            if (tour.isLiked){
                toursInteractor.deleteFromWishList(tour.id)
            } else {
                toursInteractor.addToWishList(tour.id)
            }
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