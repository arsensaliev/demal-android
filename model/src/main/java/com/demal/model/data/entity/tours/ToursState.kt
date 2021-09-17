package com.demal.model.data.entity.tours

import com.demal.model.data.entity.AppStateEntity
import com.demal.model.data.entity.category.Category

data class ToursState(
    val toursList: List<LikableTour>,
    val toursCategories: List<Category>
) : AppStateEntity
