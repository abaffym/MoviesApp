package com.abaffym.moviesapp.ui.detail

import android.arch.lifecycle.ViewModel

import com.abaffym.moviesapp.model.Movie
import com.abaffym.moviesapp.ui.MoviesRepository

class MovieDetailViewModel : ViewModel() {

    private val moviesRepository: MoviesRepository = MoviesRepository()

    private var movieId: Long = -1

    val movieDetail: Movie
        get() = moviesRepository.getMovieById(movieId)

    val isFavorite: Boolean
        get() = moviesRepository.isFavorite(movieId)

    fun setMovieId(movieId: Long) {
        this.movieId = movieId
    }

    fun addToFavorites() = moviesRepository.addToFavorites(movieId)

    fun removeFromFavorites() = moviesRepository.removeFromFavorites(movieId)
}
