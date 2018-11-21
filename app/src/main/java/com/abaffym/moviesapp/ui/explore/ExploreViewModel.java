package com.abaffym.moviesapp.ui.explore;

import com.abaffym.moviesapp.model.Movie;
import com.abaffym.moviesapp.ui.MoviesRepository;

import java.util.List;

import androidx.lifecycle.ViewModel;
import io.reactivex.Single;

public class ExploreViewModel extends ViewModel {

	private MoviesRepository movieRepository;

	ExploreViewModel() {
		this.movieRepository = new MoviesRepository();
	}

	public Single<List<Movie>> getAllMovies() {
		return movieRepository.getAllMovies();
	}
}
