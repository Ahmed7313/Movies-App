package com.example.moviesapp.domain.Model


import com.squareup.moshi.Json

data class Dates(
    @Json(name = "maximum")
    val maximum: String = "",
    @Json(name = "minimum")
    val minimum: String = ""
)