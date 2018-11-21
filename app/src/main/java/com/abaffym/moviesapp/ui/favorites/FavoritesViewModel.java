package com.abaffym.moviesapp.ui.favorites;

import com.abaffym.moviesapp.model.Movie;
import com.abaffym.moviesapp.ui.MoviesRepository;

import java.util.List;

import androidx.lifecycle.ViewModel;
import io.reactivex.Single;

public class FavoritesViewModel extends ViewModel {

	private MoviesRepository movieRepository;

	FavoritesViewModel() {
		this.movieRepository = new MoviesRepository();
	}

	public Single<List<Movie>> getFavoriteMovies() {
		return movieRepository.getFavoriteMovies();
	}

}
