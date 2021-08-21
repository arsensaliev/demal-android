package com.demal.repository.intractor

import com.demal.model.data.entity.AddToWishListEntity
import com.demal.model.data.entity.tours.LikableTour
import com.demal.model.data.entity.tours.Tours
import com.demal.model.data.entity.tours.network.Tour
import com.demal.repository.repository.LoginResponseRepositoryLocal
import com.demal.repository.repository.ToursRepository
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy

class ToursInteractorImpl(
    private val toursRepository: ToursRepository,
    private val loginResponseRepository: LoginResponseRepositoryLocal
) : ToursInteractor {

    private val userId get() = loginResponseRepository.getUser()?.id

    override suspend fun getTours(sortBy: SortBy, order: Order) =
        Tours(toLikable(toursRepository.getTours(sortBy, order)))

    override suspend fun getTourById(id: Int) =
        toLikable(toursRepository.getTourById(id))

    override suspend fun addToWishList(wishId: Int) {
        executeIfUserIdExists {
            toursRepository.addToWishList(it, AddToWishListEntity(wishId))
        }
    }

    override suspend fun deleteFromWishList(wishId: Int) {
        executeIfUserIdExists {
            toursRepository.deleteFromWishList(it, wishId)
        }
    }

    private suspend fun toLikable(toursList: List<Tour>): List<LikableTour> =
        toursList.map { toLikable(it) }

    private suspend fun toLikable(tour: Tour): LikableTour {

        val isFavorite = executeIfUserIdExists { userId ->
            toursRepository.getFavoriteTours(userId).find { it.id == tour.id } != null
        } ?: false

        return LikableTour(tour, isFavorite)
    }

    private suspend fun <T> executeIfUserIdExists(block: suspend (userIdNotNull: Int) -> T) =
        userId?.let { block(it) }

}