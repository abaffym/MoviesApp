package com.abaffym.moviesapp.data.remote.entity

import com.squareup.moshi.Json

class DiscoverMoviesResponse<T>(
        @Json(name = "page") val page: Int,
        @Json(name = "results") val results: List<T>,
        @Json(name = "total_pages") val totalPages: Int,
        @Json(name = "total_results") val totalResults: Long
)
