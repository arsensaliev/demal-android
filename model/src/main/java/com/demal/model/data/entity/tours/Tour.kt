package com.demal.model.data.entity.tours

import com.demal.model.data.entity.tours.network.TourResponse

data class Tour(
    val tour: TourResponse,
    val isFavorite: Boolean
)