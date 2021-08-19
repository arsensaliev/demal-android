package com.demal.koin.modules

import com.demal.model.data.entity.tours.domain.TourCategory
import com.demal.model.data.entity.tours.domain.DomainTour
import com.demal.model.data.entity.tours.domain.Tour
import com.demal.model.data.entity.tours.domain.TourImage
import com.demal.model.data.entity.tours.network.CategoryResponse
import com.demal.model.data.entity.tours.network.ImageResponse
import com.demal.model.data.entity.tours.network.NetworkTour
import com.demal.model.data.entity.tours.network.TourResponse
import android.widget.ImageView
import com.demal.repository.data_sources.RemoteDataSource
import com.demal.repository.data_sources.RemoteDataSourceImpl
import com.demal.repository.data_sources.preferences.GeneralPreferencesDataSource
import com.demal.repository.data_sources.preferences.PreferencesDataSourceImpl
import com.demal.repository.image.GlideImageLoader
import com.demal.repository.image.ImageLoader
import com.demal.repository.mappers.*
import com.demal.repository.repository.*
import org.koin.dsl.module

val dataSourceModule = module {
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
    single<GeneralPreferencesDataSource> { PreferencesDataSourceImpl(get()) }
}

val repositoryModule = module {
    single<TokenRepository> { TokenRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single<Mapper<CategoryResponse, TourCategory>> { CategoryMapper() }
    single<Mapper<ImageResponse, TourImage>> { ImageMapper() }
    single<ListMapper<ImageResponse, TourImage>> { ListMapperImpl(get()) }
    single<Mapper<TourResponse, Tour>> { TourMapper(get(), get()) }
    single<Mapper<NetworkTour, DomainTour>> { DomainTourMapper(get()) }
    single<ToursRepository> { ToursRepositoryImpl(get(), get()) }
}

val imageModule = module {
    single<ImageLoader<ImageView>> { GlideImageLoader() }
}