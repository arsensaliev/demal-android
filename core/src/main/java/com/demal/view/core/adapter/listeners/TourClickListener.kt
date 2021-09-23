package com.demal.view.core.adapter.listeners

import com.demal.model.data.entity.tours.LikableTour

interface TourClickListener : BaseClickListener {
    fun onLikeClick(tour: LikableTour)
    fun onItemClick(tour: LikableTour)
}