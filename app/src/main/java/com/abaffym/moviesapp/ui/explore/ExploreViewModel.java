package com.abaffym.moviesapp.ui.explore;

import android.arch.lifecycle.ViewModel;

import com.abaffym.moviesapp.model.Movie;
import com.abaffym.moviesapp.ui.MoviesRepository;

import java.util.List;

import io.reactivex.Single;

public class ExploreViewModel extends ViewModel {

    private MoviesRepository movieRepository;

    public ExploreViewModel() {
        this.movieRepository = new MoviesRepository();
    }

    public Single<List<Movie>> getAllMovies() {
        return movieRepository.getAllMovies();
    }
}
