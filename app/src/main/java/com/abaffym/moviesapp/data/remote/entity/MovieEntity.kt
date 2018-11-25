package com.abaffym.moviesapp.data.remote.entity

import com.squareup.moshi.Json

class MovieEntity(
        @Json(name = "id") val id: Long,
        @Json(name = "title") val title: String,
        @Json(name = "original_title") val originalTitle: String,
        @Json(name = "overview") val overview: String,
        @Json(name = "release_date") val releaseDate: String,
        @Json(name = "poster_path") val posterPath: String,
        @Json(name = "popularity") val popularity: Double,
        @Json(name = "vote_average") val averageVote: Double,
        @Json(name = "vote_count") val voteCount: Long,
        @Json(name = "backdrop_path") val backdropPath: String
)
