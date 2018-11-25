package com.abaffym.moviesapp.ui

import com.abaffym.moviesapp.data.local.MoviesLocalDataSource
import com.abaffym.moviesapp.data.remote.rest.MovieMapper
import com.abaffym.moviesapp.di.NetworkModule
import com.abaffym.moviesapp.model.Movie
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MoviesRepository {

    private val moviesApi = NetworkModule.moviesApi()

    private val movieMapper = MovieMapper()

    private val cachedMovies: Single<List<Movie>>
        get() = Single.fromCallable { MoviesLocalDataSource.cachedMovies }

    private val remoteMovies: Single<List<Movie>>
        get() = moviesApi.discoverMovies()
                .map { response -> response.results.map(movieMapper::mapEntityToModel) }
                .doOnSuccess { movies -> storeInLocalCache(movies) }

    val allMovies: Single<List<Movie>>
        get() = Single.concat(cachedMovies, remoteMovies)
                .filter { movies -> !movies.isEmpty() }
                .firstOrError()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    val favoriteMovies: Single<List<Movie>>
        get() = Single.fromCallable { MoviesLocalDataSource.favoriteMovies }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    fun getMovieById(id: Long) = MoviesLocalDataSource.cachedMovies
            .firstOrNull { movie -> movie.id == id }
            ?: throw IllegalStateException("MovieEntity not found")


    fun isFavorite(movieId: Long) = MoviesLocalDataSource.isFavorite(movieId)

    fun addToFavorites(movieId: Long) = MoviesLocalDataSource.addFavoriteMovie(movieId)

    fun removeFromFavorites(movieId: Long?) = MoviesLocalDataSource.removeFavoriteMovie(movieId!!)

    private fun storeInLocalCache(movies: List<Movie>) = MoviesLocalDataSource.setCachedMovies(movies)
}
