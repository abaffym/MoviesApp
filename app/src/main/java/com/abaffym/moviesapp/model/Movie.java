package com.abaffym.moviesapp.model;

import java.util.Date;
import java.util.Objects;

public class Movie {

    public Movie(long id, Date releaseDate, String posterPath, String title, double averageVote) {
        this.id = id;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.title = title;
        this.averageVote = averageVote;
    }

    private long id;

    private Date releaseDate;

    private String posterPath;

    private String title;

    private double averageVote;

    public long getId() {
        return id;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getTitle() {
        return title;
    }

    public double getAverageVote() {
        return averageVote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id &&
                Double.compare(movie.averageVote, averageVote) == 0 &&
                Objects.equals(releaseDate, movie.releaseDate) &&
                Objects.equals(posterPath, movie.posterPath) &&
                Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, releaseDate, posterPath, title, averageVote);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", releaseDate='" + releaseDate + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", title='" + title + '\'' +
                ", averageVote=" + averageVote +
                '}';
    }
}
