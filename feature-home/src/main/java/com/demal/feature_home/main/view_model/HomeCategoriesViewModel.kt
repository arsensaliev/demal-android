package com.demal.feature_home.main.view_model

import com.demal.feature_home.navigation.HomeCategoriesNavigator
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.category.Categories
import com.demal.model.data.entity.category.Category
import com.demal.repository.repository.CategoryRepository
import com.demal.view.core.view_model.BaseViewModel

class HomeCategoriesViewModel(
    private val navigator: HomeCategoriesNavigator,
    private val categoriesRepository: CategoryRepository
) : BaseViewModel<Categories>(navigator) {

    private var categories = Categories(mutableListOf())

    fun getCategories() {
        runAsync {
            mStateLiveData.postValue(BaseState.Loading(true))
            categories = categoriesRepository.getCategories()
            mStateLiveData.value = BaseState.Success(categories)
        }
    }

    fun openToursByCategory(category: Category) {
        navigator.toToursScreen(category.id)
    }

    override fun handleError(error: Throwable) {
        super.handleError(error)
        mStateLiveData.postValue(BaseState.Error(error))
    }
}