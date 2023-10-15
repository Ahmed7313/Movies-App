package com.example.moviesapp.data.remote.dto.helper

import kotlin.time.Duration.Companion.seconds

/**
 * Created by Ahmed Rabee for AREEB task on 10/14/2023
 */
object ApiParams {
    const val BASE_URL = "https://api.themoviedb.org/3/"

    //10 MB cache
    const val cacheSize = (10 * 1024 * 1024).toLong()

    object Timeouts {
        val connect = 10.seconds
        val write = 10.seconds
        val read = 10.seconds
    }
}