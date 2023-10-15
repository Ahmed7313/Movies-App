package com.example.moviesapp.domain.Model.MovieDetails


import com.squareup.moshi.Json

data class ProductionCompany(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "logo_path")
    val logoPath: String? = null,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "origin_country")
    val originCountry: String = ""
)