package com.demal.feature_home.main

import com.demal.feature_home.navigation.HomeNavigator
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.tours.LikableTours
import com.demal.repository.interactor.ToursInteractor
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy
import com.demal.view.core.view_model.BaseViewModel

class HomeViewModel(
    navigator: HomeNavigator,
    private val toursInteractor: ToursInteractor,
) : BaseViewModel<LikableTours>(navigator) {

    fun getTours() {
        runAsync {
            val tours = toursInteractor.getTours(SortBy.CREATED_AT, Order.DESCENDING)
            mStateLiveData.value = BaseState.Success(tours)
        }
    }

    fun likePressed(id : Int) {
    }

    override fun handleError(error: Throwable) {
        super.handleError(error)
        mStateLiveData.postValue(BaseState.Error(error))
    }
}