package com.demal.repository.data_sources

import com.demal.model.data.entity.AddToWishListEntity
import com.demal.model.data.entity.user.LoginRequest
import com.demal.repository.api.ApiService
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy

class RemoteDataSourceImpl(
    private val service: ApiService
) : RemoteDataSource {

    override suspend fun login(auth: LoginRequest) =
        service.login(auth).await()

    override suspend fun myUser() =
        service.myUser().await()

    override suspend fun getTours(sortBy: SortBy, order: Order) =
        service.getTours(sortBy, order).await()

    override suspend fun getTourById(id: Int) =
        service.getTourById(id).await()

    override suspend fun getUserWishList(id: Int) =
        service.getUserWishList(id).await()

    override suspend fun addToWishList(
        id: Int,
        wish: AddToWishListEntity
    ) = service.addToWishList(id, wish)

    override suspend fun deleteFromWishList(
        userId: Int,
        tourId: Int
    ) = service.deleteFromWishList(userId, tourId)
}