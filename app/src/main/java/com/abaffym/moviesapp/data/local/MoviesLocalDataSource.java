package com.abaffym.moviesapp.data.local;

import com.abaffym.moviesapp.model.Movie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MoviesLocalDataSource {

    private static List<Movie> cachedMovies = new ArrayList<>();

    private static Set<Long> favoriteMoviesIds = new HashSet<>();

    public static List<Movie> getCachedMovies() {
        return cachedMovies;
    }

    public static void setCachedMovies(List<Movie> movies) {
        cachedMovies.clear();
        cachedMovies.addAll(movies);
    }

    public static boolean isFavorite(Long movieId) {
        return favoriteMoviesIds.contains(movieId);
    }

    public static void addFavoriteMovie(Long movieId) {
        favoriteMoviesIds.add(movieId);
    }

    public static void removeFavoriteMovie(Long movieId) {
        favoriteMoviesIds.remove(movieId);
    }

    public static List<Movie> getFavoriteMovies() {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : cachedMovies) {
            if (favoriteMoviesIds.contains(movie.getId())) {
                result.add(movie);
            }
        }
        return result;
    }
}
