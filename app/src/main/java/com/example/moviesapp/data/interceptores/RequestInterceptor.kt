package com.example.moviesapp.data.interceptores

import com.example.moviesapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response



class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newUrl = originalRequest.url
            .newBuilder()
            .addQueryParameter(
                "api_key",
                BuildConfig.TMDB_API_KEY
            )
            .build()
        val request = originalRequest.newBuilder()
            .url(newUrl)
            .build()
        return chain.proceed(request)
    }
}