package com.abaffym.moviesapp.data.remote.entity

import com.squareup.moshi.Json

class DiscoverMoviesResponse<T>(
        @field:Json(name = "page") val page: Int,
        @field:Json(name = "results") val results: List<T>,
        @field:Json(name = "total_pages") val totalPages: Int,
        @field:Json(name = "total_results") val totalResults: Long
)
