package com.demal.repository.data_sources.preferences

interface GeneralPreferencesDataSource : ParcelablePreferencesDataSource,
    BooleanPreferencesDataSource {

    fun contains(key: String): Boolean

    fun remove(key: String)
}