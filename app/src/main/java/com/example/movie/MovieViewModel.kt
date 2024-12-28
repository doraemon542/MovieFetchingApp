package com.example.movie
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieViewModel : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private val repository: MovieRepository

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(MovieApiService::class.java)
        repository = MovieRepository(apiService)
    }

    fun fetchTopRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getTopRatedMovies("328eed91b076881520e24efb3a61783d")

                // Log the poster_path values for debugging
                response.results.forEach { movie ->
                    Log.d("MovieViewModel", "Title: ${movie.title}, Poster Path: ${movie.poster_path}")
                }

                _movies.postValue(response.results)
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Error fetching movies", e)
            }
        }
    }
}
