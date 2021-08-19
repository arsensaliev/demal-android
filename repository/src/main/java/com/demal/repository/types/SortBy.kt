package com.demal.repository.types

enum class SortBy(val value : String) {
    CREATED_AT("createdAt"),
    ID("id"),
    PLACE("place"),
    PRICE("price"),
    COUNTRY("country"),
    DURATION("duration"),
    TRAVELERS_COUNT("travelersCount"),
    START_DATE("startDate"),
    CATEGORY_ID("categoryId"),
}