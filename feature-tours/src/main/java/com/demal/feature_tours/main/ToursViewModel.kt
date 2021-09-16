package com.demal.feature_tours.main

import com.demal.feature_tours.navigation.ToursNavigator
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.category.Category
import com.demal.model.data.entity.tours.LikableTour
import com.demal.model.data.entity.tours.LikableTours
import com.demal.model.data.entity.tours.ToursState
import com.demal.repository.interactor.ToursInteractor
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy
import com.demal.view.core.view_model.BaseViewModel

class ToursViewModel(
    private val navigator: ToursNavigator,
    private val toursInteractor: ToursInteractor
) : BaseViewModel<ToursState>(navigator) {

    private var likableTours = LikableTours(listOf())
    private var toursCategories = mutableListOf<Category>()
    private var currentFilter: Int? = null

    override fun handleError(error: Throwable) {
        super.handleError(error)
        mStateLiveData.postValue(BaseState.Error(error))
    }

    fun getTours() {
        runAsync {
            mStateLiveData.postValue(BaseState.Loading(true))
            likableTours = toursInteractor.getTours(SortBy.ID, Order.ASCENDING)
            var categories = mutableListOf<Category>()
            likableTours.toursList.forEach {
                categories.add(it.category)
            }
            categories = categories.distinct().toMutableList()
            val difference = categories.minus(toursCategories)
            toursCategories = categories
            postTours(difference)
        }
    }

    fun setupFilter(categoryId: Int?) {
        currentFilter = categoryId
        postTours(listOf())
    }

    fun likePressed(tour: LikableTour) {
        runAsync {
            val pos = likableTours.toursList.indexOf(tour)
            val newTour = tour.copy(tour, !tour.isLiked)
            val newList = likableTours.toursList.toMutableList()
            newList[pos] = newTour
            likableTours = LikableTours(newList)
            postTours(listOf())
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

    private fun postTours(newCategories: List<Category>) {
        val toursList = if (currentFilter == null) {
            likableTours.toursList
        } else {
            val filteredTours = likableTours.toursList.filter { tour ->
                tour.categoryId == currentFilter
            }
            filteredTours
        }

        mStateLiveData.postValue(
            BaseState.Success(
                ToursState(
                    toursList,
                    newCategories
                )
            )
        )
    }
}