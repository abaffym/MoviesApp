package com.abaffym.moviesapp.model

import java.util.*

data class Movie(
        val id: Long,
        val releaseDate: Date,
        val posterPath: String,
        val title: String,
        val averageVote: Double
)
