package com.demal.repository.data_sources.preferences

interface GeneralPreferencesDataSource :
    StringPreferencesDataSource {

    fun remove(key: String)
}