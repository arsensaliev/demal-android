package com.demal.model.data.entity.tours

class LikableTour(
    private val tour: ITour,
    val isLiked: Boolean
) : ITour by tour