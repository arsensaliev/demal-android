package com.demal.view_model

import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.tours.LikableTour
import com.demal.model.data.entity.tours.LikableTours
import com.demal.navigation.MyToursNavigator
import com.demal.repository.interactor.ToursInteractor
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy
import com.demal.view.core.DateIdentifier
import com.demal.view.core.view_model.BaseViewModel

class MyToursViewModel(
    private val navigator: MyToursNavigator,
    private val interactor: ToursInteractor,
    private val dateUtill: DateIdentifier = DateIdentifier()
) :
    BaseViewModel<LikableTours>(navigator) {

    private suspend fun getTours() {
        mStateLiveData.value =
            BaseState.Success(
                interactor.getTours(
                    SortBy.CREATED_AT,
                    Order.DESCENDING
                )
            )
    }

    fun onLikeClick(tour: LikableTour) {
        runAsync {
            if (tour.isLiked) {
                interactor.deleteFromWishList(tour.id)
            } else {
                interactor.addToWishList(tour.id)
            }
            getTours()
        }
    }

    fun getActiveTour() {
        mStateLiveData.value = BaseState.Loading(true)
        runAsync {
            val tours = interactor.getTours(
                SortBy.CREATED_AT,
                Order.DESCENDING
            )
            val activeTours = LikableTours(tours.toursList.filter { dateUtill.statusTour(it) })
            mStateLiveData.value = BaseState.Success(activeTours)
        }
    }

    fun getInactiveTour() {
        mStateLiveData.value = BaseState.Loading(true)
        runAsync {
            val tours = interactor.getTours(
                SortBy.CREATED_AT,
                Order.DESCENDING
            )
            val inactiveTours = LikableTours(tours.toursList.filter { !dateUtill.statusTour(it) })
            mStateLiveData.value = BaseState.Success(inactiveTours)
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