package com.demal.view.core.adapter

import android.view.View
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import com.demal.constants.BASE_SPACES_URL
import com.demal.core.R
import com.demal.core.databinding.ItemTourBinding
import com.demal.model.data.entity.tours.LikableTour
import com.demal.repository.image.ImageLoader
import com.demal.view.core.DateIdentifier
import com.demal.view.core.adapter.listeners.TourClickListener

fun tourBind(
    view: View,
    data: LikableTour,
    listener: TourClickListener?,
    imageLoader: ImageLoader<ImageView>
) {
    val rvBinding = ItemTourBinding.bind(view)
    with(rvBinding) {
        val dif = DateIdentifier()
        val dateTour = dif.getTourDate(data)
        val dayTour = dateTour.day
        val monthTour = dif.monthsMap[dateTour.month]
        val yearTour = dateTour.year
        val date = "$dayTour $monthTour $yearTour"
        textViewPlace.text = data.place
        textViewDescription.text = data.description
        textViewPeople.text = data.travelersCount.toString()
        textViewDate.text = date

        val likeDrawable = if (data.isLiked) {
            AppCompatResources.getDrawable(rvBinding.root.context, R.drawable.ic_heart_filled)
        } else {
            AppCompatResources.getDrawable(rvBinding.root.context, R.drawable.ic_heart_empty)
        }
        imageViewLike.setImageDrawable(likeDrawable)
        imageViewLike.setOnClickListener { listener?.onLikeClick(data) }
        view.setOnClickListener { listener?.onItemClick(data) }
        imageLoader.loadImage(1, imageViewPlace, "$BASE_SPACES_URL/${data.images[0].imagePath}")
    }
}