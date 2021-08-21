package com.demal.model.data.entity.tours

import com.demal.model.data.entity.tours.network.Tour

data class TourResponse(
    val tour: Tour,
    val isFavorite: Boolean
)