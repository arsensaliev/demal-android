package com.demal.model.data.entity.tours

import com.demal.model.data.entity.AppStateEntity
import com.demal.model.data.entity.tours.network.CategoryResponse

data class ToursState(
    val toursList: List<LikableTour>,
    val toursCategories: List<CategoryResponse>
) :
    AppStateEntity
