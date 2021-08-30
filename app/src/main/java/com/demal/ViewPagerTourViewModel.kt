package com.demal

import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.tours.LikableTour
import com.demal.repository.interactor.ToursInteractor
import com.demal.view.core.view_model.BaseViewModel

class ViewPagerTourViewModel(
    navigator: ViewPagerTourNavigator,
    private val repository: ToursInteractor
) : BaseViewModel<LikableTour>(navigator) {

    fun init(id: Int) {
        runAsync {
            val tour = repository.getTourById(id = id)
            mStateLiveData.value = BaseState.Success(tour)
        }
    }


}