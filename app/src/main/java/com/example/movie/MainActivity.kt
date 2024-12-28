package com.example.movie

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView



import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider



class MainActivity : AppCompatActivity() {
    private lateinit var movieViewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        val movieAdapter = MovieAdapter { movie ->
            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra("movie_title", movie.title)
            intent.putExtra("movie_poster", movie.poster_path)
            startActivity(intent)
        }
        recyclerView.adapter = movieAdapter

        movieViewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        movieViewModel.movies.observe(this) { movies ->
            movieAdapter.submitList(movies)
        }

        movieViewModel.fetchTopRatedMovies()


    }
}