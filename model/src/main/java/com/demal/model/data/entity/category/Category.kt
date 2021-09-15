package com.demal.model.data.entity.category

import com.demal.model.data.entity.list.Content
import com.demal.model.data.entity.list.ListItem
import com.demal.model.data.entity.list.ListItemId

data class Category(
    @ListItemId val id: Int,
    @Content val name: String?,
    @Content val iconPath: String?,
    @Content val createdAt: String?,
)  : ListItem<Category>