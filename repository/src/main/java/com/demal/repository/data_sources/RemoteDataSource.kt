package com.demal.repository.data_sources

import com.demal.model.data.entity.category.Categories
import com.demal.model.data.entity.category.Category
import com.demal.model.data.entity.tours.Tour
import com.demal.model.data.entity.tours.network.AddToWishListEntity
import com.demal.model.data.entity.tours.network.MeResponse
import com.demal.model.data.entity.tours.network.Tours
import com.demal.model.data.entity.user.LoginRequest
import com.demal.model.data.entity.user.LoginResponse
import com.demal.model.data.entity.user.UserUpdate
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy

interface RemoteDataSource {
    suspend fun login(auth: LoginRequest): LoginResponse
    suspend fun myUser(): MeResponse
    suspend fun updateUser(id: Int, user: UserUpdate)
    suspend fun updateAvatar(fileByte: ByteArray?)
    suspend fun getTours(sortBy: SortBy, order: Order): List<Tour>
    suspend fun getTourById(id: Int): Tour
    suspend fun getUserWishList(id: Int): Tours
    suspend fun getCategories() : Categories
    suspend fun getCategoryById(id: Int) : Category

    suspend fun addToWishList(
        id: Int,
        wish: AddToWishListEntity
    ): Tour?

    suspend fun deleteFromWishList(
        userId: Int,
        tourId: Int
    )
}