package com.abaffym.moviesapp.ui.explore

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.abaffym.moviesapp.R
import com.abaffym.moviesapp.model.Movie
import com.bumptech.glide.Glide

class ExploreAdapter(private val onMovieClickedListener: OnMovieClickedListener?) : RecyclerView.Adapter<ExploreAdapter.ViewHolder>() {

    private val movies = mutableListOf<Movie>()

    interface OnMovieClickedListener {
        fun onMovieClicked(item: Movie)
    }

    fun setMovies(items: List<Movie>) {
        movies.clear()
        movies.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fragment_explore, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExploreAdapter.ViewHolder, position: Int) {
        val movie = movies[position]

        Glide.with(holder.view)
                .load(movie.posterPath)
                .into(holder.moviePoster)

        holder.view.setOnClickListener {
            onMovieClickedListener?.onMovieClicked(movie)
        }
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val moviePoster: ImageView = view.findViewById(R.id.item_movie_poster)
    }
}
