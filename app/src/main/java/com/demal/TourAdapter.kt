package com.demal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class TourAdapter(
    private val images: List<Int>
) : RecyclerView.Adapter<TourAdapter.ViewPagerTourViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerTourViewHolder {
        return ViewPagerTourViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view_pager, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewPagerTourViewHolder, position: Int) {
        val curImage = images[position]
        holder.itemView.findViewById<ImageView>(R.id.imageViewTour).setImageResource(curImage)
    }

    override fun getItemCount(): Int = images.size

    inner class ViewPagerTourViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}