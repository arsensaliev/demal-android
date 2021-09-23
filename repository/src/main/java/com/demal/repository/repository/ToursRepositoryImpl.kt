package com.demal.repository.repository

import com.demal.model.data.entity.tours.network.AddToWishListEntity
import com.demal.repository.data_sources.RemoteDataSource
import com.demal.repository.data_sources.WishlistDataSourceLocal
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy

class ToursRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val wishlistLocal: WishlistDataSourceLocal
) : ToursRepository {

    override suspend fun getTours(sortBy: SortBy, order: Order) =
        remoteDataSource.getTours(sortBy, order)

    override suspend fun getTourById(id: Int) =
        remoteDataSource.getTourById(id)

    override suspend fun getFavoriteTours(userId: Int) =
        wishlistLocal.tours() ?: remoteDataSource
            .getUserWishList(userId)
            .tours
            .apply {
                wishlistLocal.addAll(this)
            }

    override suspend fun addToWishList(userId: Int, wish: AddToWishListEntity) {
        remoteDataSource.addToWishList(userId, wish)?.let { tour ->
            wishlistLocal.add(tour)
        }
    }

    override suspend fun deleteFromWishList(userId: Int, tourId: Int) {
        remoteDataSource.deleteFromWishList(userId, tourId)
        wishlistLocal.delete(tourId)
    }
}