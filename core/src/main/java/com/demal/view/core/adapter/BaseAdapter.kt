package com.demal.view.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demal.model.data.entity.tours.ListItem
import com.demal.repository.image.ImageLoader
import com.demal.view.core.adapter.listeners.BaseClickListener

class BaseAdapter<T : ListItem<T>, L : BaseClickListener>(
    private val itemLayoutId: Int,
    private val bind: ((View, data: T, listener: L, imageLoader: ImageLoader<ImageView>) -> Unit)? = null,
    private val listener: L,
    private val imageLoader: ImageLoader<ImageView>

) : ListAdapter<T, BaseAdapter<T, L>.BaseViewHolder>(BaseDiffUtilCallback<T>()) {

    inner class BaseViewHolder(
        private val root: View
    ) : RecyclerView.ViewHolder(root) {
        fun bind(data: T, listener: L, imageLoader: ImageLoader<ImageView>) =
            bind?.let { it(root, data, listener, imageLoader) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(itemLayoutId, parent, false)

        return BaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position), listener, imageLoader)
    }
}