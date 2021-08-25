package com.demal.view.core.adapter

interface ListItem<T> {
    fun areContentsTheSame(other: T): Boolean
    fun areItemsTheSame(other: T): Boolean
}