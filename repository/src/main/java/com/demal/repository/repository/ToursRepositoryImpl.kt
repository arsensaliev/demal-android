package com.demal.repository.repository

import com.demal.model.data.entity.tours.Tours
import com.demal.model.data.entity.tours.network.TourResponse
import com.demal.model.data.entity.tours.Tour
import com.demal.repository.data_sources.RemoteDataSource
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy

// TODO add data source for Room
class ToursRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : ToursRepository {

    override suspend fun getTours(sortBy: SortBy, order: Order) =
        Tours(mapTours(remoteDataSource.getTours(sortBy, order)))

    override suspend fun getTourById(id: Int) =
        mapTour(remoteDataSource.getTourById(id))

    // TODO get information about likes from Room to set isFavorite
    private fun mapTours(toursList: List<TourResponse>): List<Tour> {
        return toursList.map {
            Tour(it, false)
        }
    }

    // TODO get information about likes from Room to set isFavorite
    private fun mapTour(tour: TourResponse) =
        Tour(tour, false)
}