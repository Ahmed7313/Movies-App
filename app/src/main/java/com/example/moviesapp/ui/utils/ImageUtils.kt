package com.example.moviesapp.ui.utils

/**
 * Created by Ahmed Rabee for AREEB task on 10/14/2023
 */
object ImageUtils {
    private const val BASE_URL = "https://image.tmdb.org/t/p/"
    private const val SIZE = "w500"

    fun getFullImageUrl(posterPath: String): String {
        return "$BASE_URL$SIZE$posterPath"
    }
}
