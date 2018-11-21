package com.abaffym.moviesapp.ui;

import com.abaffym.moviesapp.data.local.MoviesLocalDataSource;
import com.abaffym.moviesapp.data.remote.entity.MovieEntity;
import com.abaffym.moviesapp.data.remote.rest.DiscoverMoviesResponse;
import com.abaffym.moviesapp.data.remote.rest.MovieMapper;
import com.abaffym.moviesapp.data.remote.rest.MoviesApi;
import com.abaffym.moviesapp.di.NetworkModule;
import com.abaffym.moviesapp.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MoviesRepository {

	private MoviesApi moviesApi;

	private MovieMapper movieMapper;

	public MoviesRepository() {
		moviesApi = NetworkModule.moviesApi();
		movieMapper = new MovieMapper();
	}

	private Single<List<Movie>> getChachedMovies() {
		return Single.fromCallable(new Callable<List<Movie>>() {
			@Override
			public List<Movie> call() {
				return MoviesLocalDataSource.getCachedMovies();
			}
		});
	}

	private Single<List<Movie>> getRemoteMovies() {
		return moviesApi.discoverMovies()
				.map(new Function<DiscoverMoviesResponse<MovieEntity>, List<Movie>>() {
					@Override
					public List<Movie> apply(DiscoverMoviesResponse<MovieEntity> response) {
						List<Movie> movies = new ArrayList<>();
						for (MovieEntity entity : response.getResults()) {
							movies.add(movieMapper.mapEntityToModel(entity));
						}
						return movies;
					}
				}).doOnSuccess(new Consumer<List<Movie>>() {
					@Override
					public void accept(List<Movie> movies) {
						storeInLocalCache(movies);
					}
				});
	}

	public Single<List<Movie>> getAllMovies() {
		return Single.concat(getChachedMovies(), getRemoteMovies())
				.filter(new Predicate<List<Movie>>() {
					@Override
					public boolean test(List<Movie> movies) {
						return !movies.isEmpty();
					}
				})
				.firstOrError()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}

	public Single<List<Movie>> getFavoriteMovies() {
		return Single.fromCallable(new Callable<List<Movie>>() {
			@Override
			public List<Movie> call() {
				return MoviesLocalDataSource.getFavoriteMovies();
			}
		})
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}

	public Movie getMovieById(long id) {
		for (Movie movie : MoviesLocalDataSource.getCachedMovies()) {
			if (movie.getId() == id) {
				return movie;
			}
		}
		throw new IllegalStateException("MovieEntity not found");
	}

	public boolean isFavorite(long movieId) {
		return MoviesLocalDataSource.isFavorite(movieId);
	}

	public void addToFavorites(long movieId) {
		MoviesLocalDataSource.addFavoriteMovie(movieId);
	}

	public void removeFromFavorites(Long movieId) {
		MoviesLocalDataSource.removeFavoriteMovie(movieId);
	}

	private void storeInLocalCache(List<Movie> movies) {
		MoviesLocalDataSource.setCachedMovies(movies);
	}
}
