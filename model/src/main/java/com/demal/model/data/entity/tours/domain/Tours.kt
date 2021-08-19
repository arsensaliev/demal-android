package com.demal.model.data.entity.tours.domain

import com.demal.model.data.entity.AppStateEntity

data class Tours(val toursList : List<DomainTour>) : AppStateEntity
