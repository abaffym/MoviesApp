package com.abaffym.moviesapp.data.remote.entity;

import com.squareup.moshi.Json;

public class MovieEntity {

    @Json(name = "id")
    private long id;

    @Json(name = "original_title")
    private String originalTitle;

    @Json(name = "overview")
    private String overview;

    @Json(name = "release_date")
    private String releaseDate;

    @Json(name = "poster_path")
    private String posterPath;

    @Json(name = "popularity")
    private double popularity;

    @Json(name = "title")
    private String title;

    @Json(name = "vote_average")
    private double averageVote;

    @Json(name = "vote_count")
    private long voteCount;

    @Json(name = "backdrop_path")
    private String backdropPath;

    public MovieEntity(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAverageVote() {
        return averageVote;
    }

    public void setAverageVote(double averageVote) {
        this.averageVote = averageVote;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

}
