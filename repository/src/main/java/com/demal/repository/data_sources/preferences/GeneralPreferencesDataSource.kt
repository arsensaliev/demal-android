package com.demal.repository.data_sources.preferences

interface GeneralPreferencesDataSource : ParcelablePreferencesDataSource {

    fun remove(key: String)
}