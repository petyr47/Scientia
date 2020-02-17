package com.aneke.peter.scientia.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "adult")
    val adult: Boolean = false, // false
    @Json(name = "backdrop_path")
    val backdropPath: String = "", // /bga3i5jcejBekr2FCGJga1fYCh.jpg
    @Json(name = "genre_ids")
    val genreIds: List<Int> = listOf(),
    @Json(name = "id")
    val id: Int = 0, // 486589
    @Json(name = "original_language")
    val originalLanguage: String = "", // en
    @Json(name = "original_title")
    val originalTitle: String = "", // Red Shoes and the Seven Dwarfs
    @Json(name = "overview")
    val overview: String = "", // Princes who have been turned into Dwarfs seek the red shoes of a lady in order to break the spell, although it will not be easy.
    @Json(name = "popularity")
    val popularity: Double = 0.0, // 99.364
    @Json(name = "poster_path")
    val posterPath: String = "", // /MBiKqTsouYqAACLYNDadsjhhC0.jpg
    @Json(name = "release_date")
    val releaseDate: String = "", // 2019-07-25
    @Json(name = "title")
    val title: String = "", // Red Shoes and the Seven Dwarfs
    @Json(name = "video")
    val video: Boolean = false, // false
    @Json(name = "vote_average")
    val voteAverage: Double = 0.0, // 6.5
    @Json(name = "vote_count")
    val voteCount: Int = 0 // 104
)