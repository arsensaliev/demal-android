package com.demal.repository.intractor

import com.demal.model.data.entity.tours.LikableTour
import com.demal.model.data.entity.tours.Tours
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy

interface ToursInteractor {
    suspend fun getTours(sortBy: SortBy, order: Order): Tours
    suspend fun getTourById(id: Int): LikableTour
}