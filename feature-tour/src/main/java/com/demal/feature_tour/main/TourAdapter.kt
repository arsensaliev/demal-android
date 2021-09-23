package com.demal.feature_tour.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.demal.constants.BASE_SPACES_URL
import com.demal.feature_tour.R
import com.demal.model.data.entity.tours.network.ImageResponse
import com.demal.repository.image.ImageLoader

class TourAdapter(
    private val imageLoader: ImageLoader<ImageView>,
    private val images: List<ImageResponse>
) : RecyclerView.Adapter<TourAdapter.ViewPagerTourViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerTourViewHolder {
        return ViewPagerTourViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_pager, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewPagerTourViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.imageViewTour)
        imageLoader.loadImage(1, imageView, "$BASE_SPACES_URL/${images[position].imagePath}")
    }

    override fun getItemCount(): Int = images.size

    inner class ViewPagerTourViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}