package com.demal.view.core.adapter

import android.view.View
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import com.demal.core.R
import com.demal.core.databinding.ItemTourBinding
import com.demal.model.data.entity.tours.LikableTour
import com.demal.repository.image.GlideImageLoader
import com.demal.repository.image.ImageLoader
import com.demal.repository.image.ImageLoadingListener
import com.demal.view.core.adapter.listeners.TourClickListener


val tourBind: ((View, LikableTour, TourClickListener, ImageLoader<ImageView>) -> Unit) =
    { view, data, listener, imageLoader ->
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
            imageViewLike.setOnClickListener { listener.onLikeClick(data) }
            view.setOnClickListener { listener.onItemClick(data) }
            //TODO:Load Image
//            imageLoader.loadImage()
        }
    }