package com.abaffym.moviesapp.ui.detail;

import android.arch.lifecycle.ViewModel;

import com.abaffym.moviesapp.model.Movie;
import com.abaffym.moviesapp.ui.MoviesRepository;

public class MovieDetailViewModel extends ViewModel {

	private MoviesRepository moviesRepository;

	public MovieDetailViewModel() {
		moviesRepository = new MoviesRepository();
	}

	private long movieId = -1;

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public Movie getMovieDetail() {
		return moviesRepository.getMovieById(movieId);
	}

	public void addToFavorites() {
		moviesRepository.addToFavorites(movieId);
	}

	public void removeFromFavorites() {
		moviesRepository.removeFromFavorites(movieId);
	}

	public boolean isFavorite() {
		return moviesRepository.isFavorite(movieId);
	}
}
