package com.demal.repository.data_sources.preferences

interface GeneralPreferencesDataSource : ParcelablePreferencesDataSource,
    StringPreferencesDataSource {

    fun remove(key: String)
}