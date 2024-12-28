package com.example.movie

class ApiHelper(private val apiService: ApiService) {
    suspend fun getTopRatedMovies(apiKey: String) = apiService.getTopRatedMovies(apiKey)
}
