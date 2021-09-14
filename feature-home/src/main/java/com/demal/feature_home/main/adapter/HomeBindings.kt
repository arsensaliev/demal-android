package com.demal.feature_home.main.adapter

import android.view.View
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import com.demal.constants.BASE_SPACES_URL
import com.demal.feature_home.R
import com.demal.feature_home.databinding.HomeItemCategoryBinding
import com.demal.feature_home.databinding.HomeItemTourBinding
import com.demal.model.data.entity.category.CategoryResponse
import com.demal.model.data.entity.tours.LikableTour
import com.demal.repository.image.ImageLoader
import com.demal.view.core.adapter.listeners.TourClickListener

fun homeTourBind(
    view: View,
    data: LikableTour,
    listener: TourClickListener?,
    imageLoader: ImageLoader<ImageView>
) {
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
        homeHeartImage.setOnClickListener { listener?.onLikeClick(data) }
        view.setOnClickListener { listener?.onItemClick(data) }
        imageLoader.loadImage(
            1,
            homeBackgroundImage,
            "$BASE_SPACES_URL/${data.images[0].imagePath}"
        )
    }
}

fun homeCategoryBind(
    view: View,
    data: CategoryResponse,
    listener: CategoryClickListener?,
) {
    val rvBinding = HomeItemCategoryBinding.bind(view)
    with(rvBinding) {
        itemActivitiesText.text = data.name
        view.setOnClickListener { listener?.onItemClick(data) }
    }
}