package com.demal.feature_home.main.adapter

import com.demal.model.data.entity.category.Category
import com.demal.view.core.adapter.listeners.BaseClickListener

interface CategoryClickListener : BaseClickListener {
    fun onItemClick(category: Category)
}