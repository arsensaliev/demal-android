package com.demal.view.core.adapter.newone

import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import com.demal.core.R
import com.demal.core.databinding.ItemTourBinding
import com.demal.model.data.entity.tours.LikableTour
import com.demal.repository.image.ImageLoader
import com.demal.view.core.adapter.listeners.TourClickListener


class TourViewHolder(
    private val binding: ItemTourBinding,
    private val clickListener: TourClickListener,
    private val imageLoader: ImageLoader<ImageView>
) :
    BaseViewHolder<ItemTourBinding, LikableTour>(binding) {

    override fun bind(data: LikableTour) {
        with(binding) {
            textViewPlace.text = data.place
            textViewDescription.text = data.description
            textViewPeople.text = data.travelersCount.toString()
            textViewDate.text = data.startDate

            val likeDrawable = if (data.isLiked) {
                AppCompatResources.getDrawable(binding.root.context, R.drawable.ic_heart_filled)
            } else {
                AppCompatResources.getDrawable(binding.root.context, R.drawable.ic_heart_empty)
            }
            imageViewLike.setImageDrawable(likeDrawable)
            imageViewLike.setOnClickListener { clickListener.onLikeClick(data) }
            itemView.setOnClickListener { clickListener.onItemClick(data) }
//            imageLoader.loadImage()
        }
    }
}