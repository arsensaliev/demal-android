package com.demal.feature_home.main.view_model

import com.demal.feature_home.navigation.HomeCategoriesNavigator
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.category.CategoriesResponse
import com.demal.model.data.entity.category.CategoryResponse
import com.demal.repository.repository.CategoryRepository
import com.demal.view.core.view_model.BaseViewModel

class HomeCategoriesViewModel(
    private val navigator: HomeCategoriesNavigator,
    private val categoriesRepository: CategoryRepository
) : BaseViewModel<CategoriesResponse>(navigator) {

    private var categories = CategoriesResponse(mutableListOf())

    fun getCategories() {
        runAsync {
            mStateLiveData.postValue(BaseState.Loading(true))
            categories = categoriesRepository.getCategories()
            mStateLiveData.value = BaseState.Success(categories)
        }
    }

    fun openToursByCategory(category: CategoryResponse) {
        navigator.toToursScreen(category.id)
    }

    override fun handleError(error: Throwable) {
        super.handleError(error)
        mStateLiveData.postValue(BaseState.Error(error))
    }
}