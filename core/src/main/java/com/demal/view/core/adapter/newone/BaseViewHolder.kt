package com.demal.view.core.adapter.newone

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<B : ViewBinding, D : Any>(binding: B) :
    RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(data: D)
}