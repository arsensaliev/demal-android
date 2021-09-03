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
    private var mState: Boolean = false

    fun getTours(state: Boolean = false) {
        mState = state
        mStateLiveData.value = BaseState.Loading(true)
        runAsync {
            setLiveData(state)
        }
    }

    //          Когда будет готов запрос на бэке нужно будет изменить запрос,
//          чтобы он зависил от state
    private suspend fun setLiveData(state: Boolean) {
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
            setLiveData(mState)
        }
    }

    //      Ещё нет экрана тура
    fun onItemClick(tour: LikableTour) {}

    override fun handleError(error: Throwable) {
        super.handleError(error)
        mStateLiveData.postValue(BaseState.Error(error))
    }
}