package com.example.moviesapp.data.local.db.MovieDetails

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesapp.domain.Model.MovieDetails.Genre
import com.example.moviesapp.domain.Model.MovieDetails.MovieDetails
import com.example.moviesapp.domain.Model.MovieDetails.ProductionCompany
import com.example.moviesapp.domain.Model.MovieDetails.ProductionCountry
import com.example.moviesapp.domain.Model.MovieDetails.SpokenLanguage
import com.google.gson.Gson

/**
 * Created by Ahmed Rabee for AREEB task on 10/15/2023
 */
@Entity(tableName = "movie_details")
data class MovieDetailsEntity(
    @PrimaryKey val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val budget: Int,
    val genres: String, // Stored as JSON string
    val homepage: String,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: String, // Stored as JSON string
    val productionCountries: String, // Stored as JSON string
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: String, // Stored as JSON string
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)

fun MovieDetailsEntity.toDomainModel(): MovieDetails {
    return MovieDetails(
        id = this.id,
        adult = this.adult,
        backdropPath = this.backdropPath,
        budget = this.budget,
        genres = Gson().fromJson(this.genres, Array<Genre>::class.java).toList(),
        homepage = this.homepage,
        imdbId = this.imdbId,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        productionCompanies = Gson().fromJson(this.productionCompanies, Array<ProductionCompany>::class.java).toList(),
        productionCountries = Gson().fromJson(this.productionCountries, Array<ProductionCountry>::class.java).toList(),
        releaseDate = this.releaseDate,
        revenue = this.revenue,
        runtime = this.runtime,
        spokenLanguages = Gson().fromJson(this.spokenLanguages, Array<SpokenLanguage>::class.java).toList(),
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}