package com.demal.model.data.entity.tours

class LikableTour(
    private val tour: ITour,
    val isLiked: Boolean
) : ITour by tour, ListItem<LikableTour> {
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