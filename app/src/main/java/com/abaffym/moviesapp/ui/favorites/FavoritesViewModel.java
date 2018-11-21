package com.abaffym.moviesapp.ui.favorites;

import android.arch.lifecycle.ViewModel;

import com.abaffym.moviesapp.model.Movie;
import com.abaffym.moviesapp.ui.MoviesRepository;

import java.util.List;

import io.reactivex.Single;

public class FavoritesViewModel extends ViewModel {

	private MoviesRepository movieRepository;

	public FavoritesViewModel() {
		this.movieRepository = new MoviesRepository();
	}

	public Single<List<Movie>> getFavoriteMovies() {
		return movieRepository.getFavoriteMovies();
	}

}
