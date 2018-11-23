package com.abaffym.moviesapp.data.remote.rest;

import com.abaffym.moviesapp.data.remote.entity.DiscoverMoviesResponse;
import com.abaffym.moviesapp.data.remote.entity.MovieEntity;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface MoviesApi {

	@GET("discover/movie")
	Single<DiscoverMoviesResponse<MovieEntity>> discoverMovies();

}
