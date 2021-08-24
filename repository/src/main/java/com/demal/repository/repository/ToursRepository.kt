package com.demal.repository.repository

import com.demal.model.data.entity.AddToWishListEntity
import com.demal.model.data.entity.tours.Tour
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy

interface ToursRepository {
    suspend fun getTours(sortBy: SortBy, order: Order): List<Tour>
    suspend fun getTourById(id: Int): Tour
    suspend fun getFavoriteTours(userId: Int): List<Tour>
    suspend fun addToWishList(userId: Int, wish: AddToWishListEntity)
    suspend fun deleteFromWishList(userId: Int, tourId: Int)
}