package com.demal.repository.repository

import com.demal.model.data.entity.tours.Tour

interface ToursRepository {
    suspend fun getTours(sortBy: String, order: String) : List<Tour>
    suspend fun getTourById(id: Int) : Tour
}