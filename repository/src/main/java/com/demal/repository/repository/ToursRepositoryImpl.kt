package com.demal.repository.repository

import com.demal.model.data.entity.AddToWishListEntity
import com.demal.model.data.entity.tours.Tour
import com.demal.repository.data_sources.RemoteDataSource
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy

class ToursRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) : ToursRepository {

    private var wishList: MutableList<Tour>? = null

    override suspend fun getTours(sortBy: SortBy, order: Order) =
        remoteDataSource.getTours(sortBy, order)

    override suspend fun getTourById(id: Int) =
        remoteDataSource.getTourById(id)

    override suspend fun getFavoriteTours(userId: Int) =
        wishList ?: remoteDataSource
            .getUserWishList(userId)
            .apply {
                wishList = wishList ?: mutableListOf()
                wishList?.clear()
                wishList?.addAll(this)
            }

    override suspend fun addToWishList(userId: Int, wish: AddToWishListEntity) {
        remoteDataSource.addToWishList(userId, wish)?.let { tour ->
            wishList?.add(tour)
        }
    }

    override suspend fun deleteFromWishList(userId: Int, tourId: Int) {
        remoteDataSource.deleteFromWishList(userId, tourId)
        wishList?.removeIf { tour ->
            tour.id == tourId
        }
    }
}