package com.example.movie
import retrofit2.http.GET
import retrofit2.http.Query
interface ApiService {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): MovieResponse
}