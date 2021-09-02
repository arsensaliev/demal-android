package com.demal.feature_tours.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demal.feature_tours.navigation.ToursNavigator
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.tours.LikableTour
import com.demal.model.data.entity.tours.LikableTours
import com.demal.model.data.entity.tours.network.CategoryResponse
import com.demal.repository.interactor.ToursInteractor
import com.demal.repository.repository.UserRepository
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy
import com.demal.view.core.view_model.BaseViewModel

class ToursViewModel(
    private val navigator: ToursNavigator,
    private val toursInteractor: ToursInteractor,
    private val userRepository: UserRepository
) : BaseViewModel<LikableTours>(navigator) {

    private var likableTours = LikableTours(listOf())
    private var toursCategories = mutableListOf<CategoryResponse>()
    private var currentFilter: Int? = null

    private val mCategoriesLiveData = MutableLiveData<List<CategoryResponse>>()
    val categoriesLiveData: LiveData<List<CategoryResponse>> = mCategoriesLiveData

    override fun handleError(error: Throwable) {
        super.handleError(error)
        mStateLiveData.postValue(BaseState.Error(error))
    }

    fun getTours() {
        runAsync {
            //FOR TEST ONLY
            //TODO:REMOVE USER REPO HERE AND IN VIEWMODELMODULES
            userRepository.login("arsen@mail.ru", "password")

            mStateLiveData.postValue(BaseState.Loading(true))
            likableTours = toursInteractor.getTours(SortBy.ID, Order.ASCENDING)
            likableTours.toursList.forEach {
                toursCategories.add(it.category)
            }
            toursCategories = toursCategories.distinct().toMutableList()
            mCategoriesLiveData.postValue(toursCategories)
            postTours()
        }
    }

    fun setupFilter(categoryId: Int?) {
        currentFilter = categoryId
        postTours()
    }

    fun likePressed(tour: LikableTour) {
        runAsync {
            val pos = likableTours.toursList.indexOf(tour)
            val newTour = tour.copy(tour, !tour.isLiked)
            val newList = likableTours.toursList.toMutableList()
            newList[pos] = newTour
            likableTours = LikableTours(newList)
            postTours()
        }
        runAsync {
            if (tour.isLiked) {
                toursInteractor.deleteFromWishList(tour.id)
            } else {
                toursInteractor.addToWishList(tour.id)
            }
        }
    }

    fun openTour(tour: LikableTour) {
        navigator.toTourScreen(tour.id)
    }

    private fun postTours() {
        if (currentFilter == null) {
            mStateLiveData.postValue(BaseState.Success(likableTours))
        } else {
            val filteredTours = likableTours.toursList.filter { tour ->
                tour.categoryId == currentFilter
            }
            mStateLiveData.postValue(BaseState.Success(LikableTours(filteredTours)))
        }
    }
}