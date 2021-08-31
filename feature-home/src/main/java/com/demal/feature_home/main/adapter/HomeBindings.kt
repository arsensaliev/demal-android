package com.demal.feature_home.main.adapter

import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import com.demal.feature_home.R
import com.demal.feature_home.databinding.HomeItemTourBinding
import com.demal.model.data.entity.tours.LikableTour
import com.demal.view.core.adapter.listeners.TourClickListener

val homeTourBind: ((View, LikableTour, TourClickListener) -> Unit) = { view, data, listener ->
    val rvBinding = HomeItemTourBinding.bind(view)
    with(rvBinding) {
        itemHomeHeaderText.text = data.place
        itemHomeDescriptionText.text = rvBinding.root.context.resources
            .getString(R.string.duration_text, data.duration)
        itemHomeHumanCountText.text = data.travelersCount.toString()

        val likeDrawable = if (data.isLiked) {
            AppCompatResources.getDrawable(rvBinding.root.context, R.drawable.ic_heart_filled_white)
        } else {
            AppCompatResources.getDrawable(rvBinding.root.context, R.drawable.ic_heart_empty_white)
        }
        homeHeartImage.setImageDrawable(likeDrawable)
        homeHeartImage.setOnClickListener { listener.onLikeClick(data.id) }
        view.setOnClickListener { listener.onItemClick(data) }
    }
}