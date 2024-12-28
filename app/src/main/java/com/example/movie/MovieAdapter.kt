package com.example.movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.R


class MovieAdapter(private val onClick: (Movie) -> Unit) :
    ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MovieViewHolder(itemView: View, val onClick: (Movie) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.movie_title)
        private val poster: ImageView = itemView.findViewById(R.id.movie_poster)

        fun bind(movie: Movie) {
            title.text = movie.title
            Glide.with(itemView.context)
                .load("http://image.tmdb.org/t/p/w185" + movie.poster_path)
                .into(poster)

            itemView.setOnClickListener { onClick(movie) }
        }
    }

    class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
    }
}
