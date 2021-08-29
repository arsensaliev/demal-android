package com.demal.repository.data_sources.preferences

import android.os.Parcelable

interface ParcelablePreferencesDataSource {
    fun <T : Parcelable> getParcelable(key: String, tClass: Class<T>): T?
    fun <T : Parcelable> putParcelable(key: String, value: T)
}