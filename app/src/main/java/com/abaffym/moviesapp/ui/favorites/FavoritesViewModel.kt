package com.abaffym.moviesapp.ui.favorites

import android.arch.lifecycle.ViewModel
import com.abaffym.moviesapp.ui.MoviesRepository

class FavoritesViewModel : ViewModel() {

    private val movieRepository: MoviesRepository = MoviesRepository()

    val favoriteMovies = movieRepository.favoriteMovies

}
