package com.test.com.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.com.data.Movie
import com.test.com.databinding.AdapterMovieBinding
import javax.inject.Inject

class MovieAdapter @Inject constructor() : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var movies = mutableListOf<Movie>()

    fun updateMovies(movies: List<Movie>) {
        this.movies = movies.toMutableList()
        notifyItemRangeInserted(0, movies.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            AdapterMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.view.tvTitle.text = movie.title
        Picasso.with(holder.view.imgMovieImage.context).load(movie.thumbnailUrl).into(holder.view.imgMovieImage);

    }

    override fun getItemCount(): Int {
        return movies.size
    }


    class MovieViewHolder(val view: AdapterMovieBinding) : RecyclerView.ViewHolder(view.root)
}