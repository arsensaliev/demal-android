package com.demal.model.data.entity.category

import com.demal.model.data.entity.list.ListItem
import com.demal.model.data.entity.tours.network.CategoryResponse

data class Category(private val category: CategoryResponse) : ICategory by category,
    ListItem<Category> {
    override fun areContentsTheSame(other: Category): Boolean {
        return this.id == other.id
    }

    override fun areItemsTheSame(other: Category): Boolean {
        return this.name == other.name &&
                this.iconPath == other.iconPath &&
                this.createdAt == other.createdAt
    }
}

