package com.demal.view.core.adapter.newone

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.demal.core.databinding.ItemTourBinding
import com.demal.model.data.entity.tours.LikableTour
import com.demal.repository.image.ImageLoader
import com.demal.view.core.adapter.listeners.TourClickListener


class ToursAdapter(
    private val tourClickListener: TourClickListener,
    private val imageLoader: ImageLoader<ImageView>
) : BaseAdapter<LikableTour, ItemTourBinding, TourViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTourBinding.inflate(inflater, parent, false)
        return TourViewHolder(binding, tourClickListener, imageLoader)
    }
}