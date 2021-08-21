package com.demal.repository.data_sources

import com.demal.model.data.entity.AddToWishListEntity
import com.demal.model.data.entity.tours.network.Tour
import com.demal.model.data.entity.user.LoginRequest
import com.demal.model.data.entity.user.LoginResponse
import com.demal.model.data.entity.user.User
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy

interface RemoteDataSource {
    suspend fun login(auth: LoginRequest): LoginResponse
    suspend fun myUser(): User
    suspend fun getTours(sortBy: SortBy, order: Order): List<Tour>
    suspend fun getTourById(id: Int): Tour
    suspend fun getUserWishList(id: Int): List<Tour>

    suspend fun addToWishList(
        id: Int,
        wish: AddToWishListEntity
    ): Tour?

    suspend fun deleteFromWishList(
        userId: Int,
        tourId: Int
    )
}