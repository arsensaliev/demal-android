package com.demal.repository.data_sources

import com.demal.model.data.entity.tours.network.AddToWishListEntity
import com.demal.model.data.entity.tours.Tour
import com.demal.model.data.entity.tours.network.MeResponse
import com.demal.model.data.entity.tours.network.Tours
import com.demal.model.data.entity.user.LoginRequest
import com.demal.model.data.entity.user.LoginResponse
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy

interface RemoteDataSource {
    suspend fun login(auth: LoginRequest): LoginResponse
    suspend fun myUser(): MeResponse
    suspend fun getTours(sortBy: SortBy, order: Order): List<Tour>
    suspend fun getTourById(id: Int): Tour
    suspend fun getUserWishList(id: Int): Tours

    suspend fun addToWishList(
        id: Int,
        wish: AddToWishListEntity
    ): Tour?

    suspend fun deleteFromWishList(
        userId: Int,
        tourId: Int
    )
}