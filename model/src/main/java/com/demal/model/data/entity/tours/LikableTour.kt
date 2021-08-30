package com.demal.model.data.entity.tours

import com.demal.model.data.entity.AppStateEntity
import com.demal.model.data.entity.list.ListItem

class LikableTour(
    private val tour: ITour,
    val isLiked: Boolean
) : ITour by tour, ListItem<LikableTour>, AppStateEntity {
    override fun areItemsTheSame(other: LikableTour): Boolean {
        return this.id == other.id
    }

    override fun areContentsTheSame(other: LikableTour): Boolean {
        return this.isLiked == other.isLiked &&
                this.place == other.place &&
                this.description == other.description &&
                this.travelersCount == other.travelersCount &&
                this.startDate == other.startDate
    }
}