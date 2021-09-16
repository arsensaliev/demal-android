package com.demal.repository.data_sources

import com.demal.model.data.entity.tours.network.AddToWishListEntity
import com.demal.model.data.entity.user.LoginRequest
import com.demal.model.data.entity.user.RegistrationRequest
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

    override suspend fun getCategories() =
        service.getCategories().await()

    override suspend fun getCategoryById(id: Int) =
        service.getCategoryById(id).await()

    override suspend fun register(registrationRequest: RegistrationRequest) {
        service.register(registrationRequest).await()
    }

    override suspend fun getUserWishList(id: Int) =
        service.getUserWishList(id).await()

    override suspend fun addToWishList(
        id: Int,
        wish: AddToWishListEntity
    ) = service.addToWishList(id, wish).await()?.tour

    override suspend fun deleteFromWishList(
        userId: Int,
        tourId: Int
    ) = service.deleteFromWishList(userId, tourId).await()
}