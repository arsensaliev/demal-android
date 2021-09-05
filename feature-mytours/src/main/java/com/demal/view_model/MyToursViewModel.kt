package com.demal.view_model

import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.tours.LikableTour
import com.demal.model.data.entity.tours.LikableTours
import com.demal.navigation.MyToursNavigator
import com.demal.repository.interactor.ToursInteractor
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy
import com.demal.view.core.view_model.BaseViewModel

class MyToursViewModel(navigator: MyToursNavigator, private val interactor: ToursInteractor) :
    BaseViewModel<LikableTours>(navigator) {

    fun getTours() {
        mStateLiveData.value = BaseState.Loading(true)
        runAsync {
            setLiveData()
        }
    }

    private suspend fun setLiveData() {
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
            setLiveData()
        }
    }

    //      Ещё нет экрана тура
    fun onItemClick(tour: LikableTour) {}

    override fun handleError(error: Throwable) {
        super.handleError(error)
        mStateLiveData.postValue(BaseState.Error(error))
    }
}