package com.abaffym.moviesapp.di

import com.abaffym.moviesapp.data.remote.rest.AuthorizationInterceptor
import com.abaffym.moviesapp.data.remote.rest.MoviesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {

    const val POSTER_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val CONNECT_TIMEOUT = 15
    private const val WRITE_TIMEOUT = 60
    private const val TIMEOUT = 60

    private val okHttpClient: OkHttpClient by lazy {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                .addInterceptor(AuthorizationInterceptor())
                .addInterceptor(loggingInterceptor)

        builder.build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    fun moviesApi(): MoviesApi = retrofit.create(MoviesApi::class.java)

}
