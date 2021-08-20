package com.demal.model.data.entity.tours

import com.demal.model.data.entity.AppStateEntity
import com.demal.model.data.entity.tours.network.TourResponse

data class Tours(val toursList : List<TourResponse>) : AppStateEntity