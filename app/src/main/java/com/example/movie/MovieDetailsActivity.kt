package com.example.movie

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movieTitle: TextView = findViewById(R.id.movie_details_title)
        val moviePoster: ImageView = findViewById(R.id.movie_details_poster)

        val title = intent.getStringExtra("movie_title")
        val posterPath = intent.getStringExtra("movie_poster")

        movieTitle.text = title
        Glide.with(this)
            .load("http://image.tmdb.org/t/p/w185" + posterPath)
            .into(moviePoster)
    }
}
