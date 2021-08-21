package com.demal.repository.repository

import com.demal.model.data.entity.tours.network.Tour
import com.demal.repository.data_sources.RemoteDataSource
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy

class ToursRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : ToursRepository {

    private var wishList: List<Tour>? = null

    override suspend fun getTours(sortBy: SortBy, order: Order) =
        remoteDataSource.getTours(sortBy, order)

    override suspend fun getTourById(id: Int) =
        remoteDataSource.getTourById(id)

    override suspend fun getFavoriteTours(userId: Int) =
        wishList ?: remoteDataSource
            .getUserWishList(userId)
            .apply {
                wishList = this
            }
}