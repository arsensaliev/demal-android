package com.demal.feature_wishlist.main

import com.demal.feature_wishlist.navigation.WishlistNavigator
import com.demal.model.data.app_state.BaseState
import com.demal.model.data.entity.tours.LikableTour
import com.demal.model.data.entity.tours.LikableTours
import com.demal.repository.interactor.ToursInteractor
import com.demal.repository.repository.UserRepository
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy
import com.demal.view.core.view_model.BaseViewModel

class WishlistViewModel(
    navigator: WishlistNavigator,
    private val toursInteractor: ToursInteractor,
    private val userRepository: UserRepository
) : BaseViewModel<LikableTours>(navigator) {

    private var likableTours = LikableTours(listOf())

    fun getLikedTours() {
        runAsync {

            //FOR TEST ONLY
            //TODO:REMOVE USER REPO HERE AND IN VIEWMODELMODULES
            userRepository.login("arsen@mail.ru", "password")

            mStateLiveData.postValue(BaseState.Loading(true))
//            toursList = toursInteractor.getFavoriteTours()
            likableTours = toursInteractor.getTours(SortBy.ID, Order.ASCENDING)
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
        }
    }

    override fun handleError(error: Throwable) {
        super.handleError(error)
        mStateLiveData.postValue(BaseState.Error(error))
    }
}