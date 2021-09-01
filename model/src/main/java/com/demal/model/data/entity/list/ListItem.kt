package com.demal.model.data.entity.list

interface ListItem<T> {
    fun areContentsTheSame(other: T) = compareAnnotatedFields(other, Content::class.java)
    fun areItemsTheSame(other: T) = compareAnnotatedFields(other, ListItemId::class.java)
}