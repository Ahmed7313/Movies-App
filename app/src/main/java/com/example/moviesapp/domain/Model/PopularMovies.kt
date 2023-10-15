package com.example.moviesapp.domain.Model


import com.squareup.moshi.Json

data class PopularMovies(
    @Json(name = "dates")
    val dates: Dates = Dates(),
    @Json(name = "page")
    val page: Int = 0,
    @Json(name = "results")
    val results: List<Movie> = listOf(),
    @Json(name = "total_pages")
    val totalPages: Int = 0,
    @Json(name = "total_results")
    val totalResults: Int = 0
)