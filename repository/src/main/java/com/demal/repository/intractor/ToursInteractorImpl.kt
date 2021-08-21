package com.demal.repository.intractor

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

    override suspend fun getTours(sortBy: SortBy, order: Order) =
        Tours(toLikable(toursRepository.getTours(sortBy, order)))

    override suspend fun getTourById(id: Int) =
        toLikable(toursRepository.getTourById(id))

    private suspend fun toLikable(toursList: List<Tour>): List<LikableTour> =
        toursList.map { toLikable(it) }

    private suspend fun toLikable(tour: Tour): LikableTour{
        val isFavorite = loginResponseRepository.getUser()?.id?.let { uid ->
            toursRepository.getFavoriteTours(uid).find { it.id == tour.id } != null
        } ?: false


        return LikableTour(tour, isFavorite)
    }
}