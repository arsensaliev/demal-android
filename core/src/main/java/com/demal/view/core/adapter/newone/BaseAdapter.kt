package com.demal.view.core.adapter.newone

import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.demal.model.data.entity.list.ListItem
import com.demal.view.core.adapter.BaseDiffUtilCallback

abstract class BaseAdapter<D : ListItem<D>, B : ViewBinding, VH : BaseViewHolder<B, D>> :
    ListAdapter<D, VH>(BaseDiffUtilCallback<D>()) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}