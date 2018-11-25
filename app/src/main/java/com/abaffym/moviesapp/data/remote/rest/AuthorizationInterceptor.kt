package com.abaffym.moviesapp.data.remote.rest

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

private const val API_KEY_PARAM = "api_key"
private const val API_KEY = "c3e426c048101935674497dcc30cf7b7"

class AuthorizationInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val originalHttpUrl = originalRequest.url()
        val newHttpUrl = originalHttpUrl.newBuilder()
                .setQueryParameter(API_KEY_PARAM, API_KEY)
                .build()

        val newRequest = originalRequest.newBuilder()
                .url(newHttpUrl)
                .build()

        return chain.proceed(newRequest)
    }
}
