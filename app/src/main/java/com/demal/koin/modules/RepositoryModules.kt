package com.demal.koin.modules

import com.demal.repository.data_sources.RemoteDataSource
import com.demal.repository.data_sources.RemoteDataSourceImpl
import com.demal.repository.repository.UserRepository
import com.demal.repository.repository.UserRepositoryImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
}

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
}
