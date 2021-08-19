package com.demal.repository.mappers

import com.demal.model.data.entity.tours.domain.TourCategory
import com.demal.model.data.entity.tours.domain.DomainTour
import com.demal.model.data.entity.tours.domain.TourImage
import com.demal.model.data.entity.tours.domain.Tour
import com.demal.model.data.entity.tours.network.CategoryResponse
import com.demal.model.data.entity.tours.network.ImageResponse
import com.demal.model.data.entity.tours.network.NetworkTour
import com.demal.model.data.entity.tours.network.TourResponse

class DomainTourMapper(
    private val tourMapper: Mapper<TourResponse, Tour>,
) :
    Mapper<NetworkTour, DomainTour> {

    override fun map(input: NetworkTour): DomainTour {
        return DomainTour(
            tourMapper.map(input.tour),
            input.isFavorite
        )
    }
}

class TourMapper(
    private val categoryMapper: Mapper<CategoryResponse, TourCategory>,
    private val imagesMapper: ListMapper<ImageResponse, TourImage>
) : Mapper<TourResponse, Tour> {
    override fun map(input: TourResponse) =
        Tour(
            input.id,
            input.title,
            input.subTitle,
            input.place,
            input.price,
            input.country,
            input.duration,
            input?.description ?: "",
            input.travelersCount,
            input.startDate,
            input.categoryId, input.createdAt,
            imagesMapper.map(input.images),
            categoryMapper.map(input.category),
        )
}

class CategoryMapper : Mapper<CategoryResponse, TourCategory> {
    override fun map(input: CategoryResponse) = TourCategory(
        input.id,
        input.name,
        input.iconPath,
        input.createdAt,
    )
}

class ImageMapper : Mapper<ImageResponse, TourImage> {
    override fun map(input: ImageResponse) = TourImage(
        input.id,
        input.tourId,
        input.imagePath,
        input.createdAt,
    )
}
