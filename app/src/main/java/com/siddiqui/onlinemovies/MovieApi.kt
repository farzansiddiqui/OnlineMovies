package com.siddiqui.onlinemovies

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("popular?")
    fun getPopularMovies(@Query("api_key")apiKey:String):Call<Movies>
}