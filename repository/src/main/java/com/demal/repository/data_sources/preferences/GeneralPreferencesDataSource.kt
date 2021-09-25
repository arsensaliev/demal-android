package com.demal.repository.data_sources.preferences

interface GeneralPreferencesDataSource : ParcelablePreferencesDataSource,
    BooleanPreferencesDataSource {

    fun remove(key: String)
}