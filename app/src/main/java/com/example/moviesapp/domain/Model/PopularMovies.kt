package com.softaai.mvvmdemo.domain.model


data class PopularMovies(
    val page: Int,
    val results: List<Movie>
)
