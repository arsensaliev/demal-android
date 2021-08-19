package com.demal.repository.repository

import com.demal.model.data.entity.tours.domain.DomainTour
import com.demal.model.data.entity.tours.domain.Tours
import com.demal.model.data.entity.tours.network.NetworkTour
import com.demal.model.data.entity.tours.network.TourResponse
import com.demal.repository.data_sources.RemoteDataSource
import com.demal.repository.mappers.Mapper
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy

// TODO add data source for Room
class ToursRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val toursDataMapper: Mapper<NetworkTour, DomainTour>,
) : ToursRepository {

    override suspend fun getTours(sortBy: SortBy, order: Order) =
        Tours(mapTours(remoteDataSource.getTours(sortBy, order)))

    override suspend fun getTourById(id: Int) =
        mapTour(remoteDataSource.getTourById(id))

    // TODO get information about likes from Room to set isFavorite
    private fun mapTours(toursList: List<TourResponse>): List<DomainTour> {
        return toursList.map {
            toursDataMapper.map(NetworkTour(it, false))
        }
    }

    // TODO get information about likes from Room to set isFavorite
    private fun mapTour(tour: TourResponse) =
        toursDataMapper.map(NetworkTour(tour, false))
}