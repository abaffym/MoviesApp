package com.abaffym.moviesapp.data.remote.entity

import com.squareup.moshi.Json

class MovieEntity(
        @field:Json(name = "id") val id: Long,
        @field:Json(name = "title") val title: String,
        @field:Json(name = "original_title") val originalTitle: String,
        @field:Json(name = "overview") val overview: String,
        @field:Json(name = "release_date") val releaseDate: String,
        @field:Json(name = "poster_path") val posterPath: String,
        @field:Json(name = "popularity") val popularity: Double,
        @field:Json(name = "vote_average") val averageVote: Double,
        @field:Json(name = "vote_count") val voteCount: Long,
        @field:Json(name = "backdrop_path") val backdropPath: String
)
