package com.demal.repository.repository
import com.demal.model.data.entity.tours.domain.DomainTour
import com.demal.model.data.entity.tours.domain.Tours
import com.demal.repository.types.Order
import com.demal.repository.types.SortBy

interface ToursRepository {
    suspend fun getTours(sortBy: SortBy, order: Order) : Tours
    suspend fun getTourById(id: Int) : DomainTour
}