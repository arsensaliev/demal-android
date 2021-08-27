package com.demal.view.core.adapter

import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import com.demal.core.R
import com.demal.core.databinding.ItemTourBinding
import com.demal.model.data.entity.tours.LikableTour
import com.demal.view.core.adapter.listeners.TourClickListener


val tourBind: ((View, LikableTour, TourClickListener) -> Unit) = { view, data, listener ->
    val rvBinding = ItemTourBinding.bind(view)
    with(rvBinding) {
        textViewPlace.text = data.place
        textViewDescription.text = data.description
        textViewPeople.text = data.travelersCount.toString()
        textViewDate.text = data.startDate

        val likeDrawable = if (data.isLiked) {
            AppCompatResources.getDrawable(rvBinding.root.context, R.drawable.ic_heart_filled)
        } else {
            AppCompatResources.getDrawable(rvBinding.root.context, R.drawable.ic_heart_empty)
        }
        imageViewLike.setImageDrawable(likeDrawable)
        imageViewLike.setOnClickListener { listener.onLikeClick(data.id) }
        view.setOnClickListener { listener.onItemClick(data) }
    }
}