package com.demal.feature_wishlist.main

import com.demal.feature_wishlist.navigation.WishlistNavigator
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.tours.LikableTour
import com.demal.model.data.entity.tours.LikableTours
import com.demal.repository.interactor.ToursInteractor
import com.demal.view.core.view_model.BaseViewModel

class WishlistViewModel(
    private val navigator: WishlistNavigator,
    private val toursInteractor: ToursInteractor
) : BaseViewModel<LikableTours>(navigator) {

    private var likableTours = LikableTours(listOf())

    fun getLikedTours() {
        runAsync {
            mStateLiveData.postValue(BaseState.Loading(true))
            likableTours = toursInteractor.getFavoriteTours() ?: LikableTours(listOf())
            mStateLiveData.postValue(BaseState.Success(likableTours))
        }
    }

    fun likePressed(tour: LikableTour) {
        runAsync {
            val pos = likableTours.toursList.indexOf(tour)
            val newTour = tour.copy(tour, !tour.isLiked)
            val newList = likableTours.toursList.toMutableList()
            newList[pos] = newTour
            likableTours = LikableTours(newList)
            mStateLiveData.postValue(BaseState.Success(likableTours))
        }
        runAsync {
            if (tour.isLiked) {
                toursInteractor.deleteFromWishList(tour.id)
            } else {
                toursInteractor.addToWishList(tour.id)
            }
            getLikedTours()
        }
    }

    fun openTour(tour: LikableTour) {
        navigator.toTourScreen(tour.id)
    }

    override fun handleError(error: Throwable) {
        super.handleError(error)
        mStateLiveData.postValue(BaseState.Error(error))
    }
}