package com.demal.model.data.entity.tours.domain

import com.demal.model.data.entity.AppStateEntity

data class DomainTour(
    val tour : Tour,
    val isFavorite: Boolean,
) : AppStateEntity