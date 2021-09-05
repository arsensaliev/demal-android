package com.demal.repository.data_sources

import com.demal.model.data.entity.tours.Tour

interface WishlistDataSourceLocal {
    suspend fun tours(): List<Tour>?
    suspend fun delete(tourId: Int)
    suspend fun add(tour: Tour)
    suspend fun deleteAll()
    suspend fun addAll(tours: List<Tour>)
}