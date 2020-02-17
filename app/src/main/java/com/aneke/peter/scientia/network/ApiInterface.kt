package com.aneke.peter.scientia.network

import com.aneke.peter.scientia.models.DiscoverTopRatedNetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {


    @GET("discover/movie")
    suspend fun getTopRatedMovies( @Query("api_key")key : String,
                                   @Query("language") lang : String = "en-US",
                                   @Query("sort_by") sort : String = "popularity.desc"
                                   ) : DiscoverTopRatedNetworkResponse

}