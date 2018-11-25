package com.abaffym.moviesapp.data.remote.rest;

import com.abaffym.moviesapp.data.remote.entity.MovieEntity;
import com.abaffym.moviesapp.di.NetworkModule;
import com.abaffym.moviesapp.model.Movie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MovieMapper {

    public Movie mapEntityToModel(MovieEntity entity) {
        return new Movie(
                entity.getId(),
                parseApiDate(entity.getReleaseDate()),
                NetworkModule.POSTER_IMAGE_BASE_URL + entity.getPosterPath(),
                entity.getTitle(),
                entity.getAverageVote()
        );
    }

    private Date parseApiDate(String textDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            return dateFormat.parse(textDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid API date.");
        }
    }

}
