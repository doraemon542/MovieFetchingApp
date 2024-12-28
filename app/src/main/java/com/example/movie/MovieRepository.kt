package com.example.movie



class MovieRepository(private val apiService: MovieApiService) {
    suspend fun getTopRatedMovies(apiKey: String): MovieResponse {
        return apiService.getTopRatedMovies(apiKey)
    }
}
