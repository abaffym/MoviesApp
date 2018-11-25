package com.abaffym.moviesapp.data.remote.rest

import com.abaffym.moviesapp.data.remote.entity.MovieEntity
import com.abaffym.moviesapp.di.NetworkModule
import com.abaffym.moviesapp.model.Movie
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class MovieMapper {

    fun mapEntityToModel(entity: MovieEntity) = Movie(
            entity.id,
            parseApiDate(entity.releaseDate),
            NetworkModule.POSTER_IMAGE_BASE_URL + entity.posterPath,
            entity.title,
            entity.averageVote
    )

    private fun parseApiDate(textDate: String): Date = try {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        dateFormat.parse(textDate)
    } catch (e: ParseException) {
        throw IllegalArgumentException("Invalid API date.")
    }

}
