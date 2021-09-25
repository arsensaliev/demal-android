package com.demal.view_model

import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.tours.LikableTour
import com.demal.model.data.entity.tours.LikableTours
import com.demal.navigation.MyToursNavigator
import com.demal.repository.interactor.ToursInteractor
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy
import com.demal.utils.date.DateIdentifier
import com.demal.view.core.view_model.BaseViewModel

class MyToursViewModel(
    private val navigator: MyToursNavigator,
    private val interactor: ToursInteractor,
    private val dateUtill: DateIdentifier = DateIdentifier()
) :
    BaseViewModel<LikableTours>(navigator) {

    fun onLikeClick(tour: LikableTour, stateView: Boolean) {
        runAsync {
            if (tour.isLiked) {
                interactor.deleteFromWishList(tour.id)
            } else {
                interactor.addToWishList(tour.id)
            }
            if (stateView){
                getActiveTour()
            } else{
                getInactiveTour()
            }
        }
    }

    fun getActiveTour() {
        mStateLiveData.postValue(BaseState.Loading(true))
        runAsync {
            val tours = interactor.getTours(
                SortBy.CREATED_AT,
                Order.DESCENDING
            )
            val activeTours = LikableTours(tours.toursList.filter { dateUtill.statusTour(it.startDate) })
            mStateLiveData.postValue(BaseState.Success(activeTours))
        }
    }

    fun getInactiveTour() {
        mStateLiveData.postValue(BaseState.Loading(true))
        runAsync {
            val tours = interactor.getTours(
                SortBy.CREATED_AT,
                Order.DESCENDING
            )
            val inactiveTours = LikableTours(tours.toursList.filter { !dateUtill.statusTour(it.startDate) })
            mStateLiveData.postValue(BaseState.Success(inactiveTours))
        }
    }

    fun onItemClick(tourId: Int) {
        navigator.toTourScreen(tourId)
    }

    override fun handleError(error: Throwable) {
        super.handleError(error)
        mStateLiveData.postValue(BaseState.Error(error))
    }
}