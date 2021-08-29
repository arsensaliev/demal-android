package com.demal.model.data.entity.tours

interface ListItem<T> {
    fun areContentsTheSame(other: T): Boolean
    fun areItemsTheSame(other: T): Boolean
}