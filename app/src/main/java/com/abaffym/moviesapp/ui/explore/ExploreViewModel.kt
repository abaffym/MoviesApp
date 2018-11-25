package com.abaffym.moviesapp.ui.explore

import android.arch.lifecycle.ViewModel
import com.abaffym.moviesapp.ui.MoviesRepository

class ExploreViewModel : ViewModel() {

    private val movieRepository: MoviesRepository = MoviesRepository()

    val allMovies = movieRepository.allMovies

}
