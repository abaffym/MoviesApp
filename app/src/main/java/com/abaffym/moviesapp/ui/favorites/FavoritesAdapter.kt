package com.abaffym.moviesapp.ui.favorites

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.abaffym.moviesapp.R
import com.abaffym.moviesapp.model.Movie
import com.bumptech.glide.Glide

class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    private val movies = mutableListOf<Movie>()

    fun setMovies(items: List<Movie>) {
        movies.clear()
        movies.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_fragment_favorites, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]

        holder.movieTitle.text = movie.title

        Glide.with(holder.view)
                .load(movie.posterPath)
                .into(holder.moviePoster)
    }

    override fun getItemCount() = movies.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val movieTitle: TextView = view.findViewById(R.id.item_movie_title)
        val moviePoster: ImageView = view.findViewById(R.id.item_movie_poster)
    }
}
