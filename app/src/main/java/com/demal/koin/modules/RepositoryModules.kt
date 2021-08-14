package com.demal.koin.modules

import com.demal.repository.data_sources.RemoteDataSource
import com.demal.repository.data_sources.RemoteDataSourceImpl
import com.demal.repository.repository.TokenRepository
import com.demal.repository.repository.TokenRepositoryImpl
import com.demal.repository.data_sources.preferences.GeneralPreferencesDataSource
import com.demal.repository.data_sources.preferences.PreferencesDataSourceImpl
import com.demal.repository.repository.UserRepository
import com.demal.repository.repository.UserRepositoryImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
    single<GeneralPreferencesDataSource> { PreferencesDataSourceImpl(get()) }
}

val repositoryModule = module {
    single<TokenRepository> { TokenRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
}
