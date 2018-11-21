package com.abaffym.moviesapp.di;

import com.abaffym.moviesapp.data.remote.rest.AuthorizationInterceptor;
import com.abaffym.moviesapp.data.remote.rest.MoviesApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class NetworkModule {

	public static final String POSTER_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";

	private static final String BASE_URL = "https://api.themoviedb.org/3/";
	private static final int CONNECT_TIMEOUT = 15;
	private static final int WRITE_TIMEOUT = 60;
	private static final int TIMEOUT = 60;

	private static OkHttpClient getOkHttpClient() {
		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

		OkHttpClient.Builder builder = new OkHttpClient.Builder()
				.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
				.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
				.readTimeout(TIMEOUT, TimeUnit.SECONDS)
				.addInterceptor(new AuthorizationInterceptor())
				.addInterceptor(loggingInterceptor);

		return builder.build();
	}

	private static Retrofit getRetrofit() {
		return new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(MoshiConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.client(getOkHttpClient())
				.build();
	}

	public static MoviesApi moviesApi() {
		return getRetrofit().create(MoviesApi.class);
	}

}
