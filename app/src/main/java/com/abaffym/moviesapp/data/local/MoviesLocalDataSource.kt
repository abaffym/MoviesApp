package com.abaffym.moviesapp.data.local

import com.abaffym.moviesapp.model.Movie
import java.util.*

object MoviesLocalDataSource {

    val cachedMovies = ArrayList<Movie>()

    private val favoriteMoviesIds = HashSet<Long>()

    val favoriteMovies
        get() = cachedMovies.filter { movie -> favoriteMoviesIds.contains(movie.id) }

    fun setCachedMovies(movies: List<Movie>) {
        cachedMovies.clear()
        cachedMovies.addAll(movies)
    }

    fun addFavoriteMovie(movieId: Long) {
        favoriteMoviesIds.add(movieId)
    }

    fun removeFavoriteMovie(movieId: Long) {
        favoriteMoviesIds.remove(movieId)
    }

    fun isFavorite(movieId: Long): Boolean = favoriteMoviesIds.contains(movieId)

}
