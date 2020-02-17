package com.aneke.peter.scientia.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DiscoverTopRatedNetworkResponse(

    @Json(name = "page")
    val page: Int = 0, // 1

    @Json(name = "results")
    val movies: List<Movie> = listOf(),

    @Json(name = "total_pages")
    val totalPages: Int = 0,

    @Json(name = "total_results")
    val totalResults: Int = 0
)