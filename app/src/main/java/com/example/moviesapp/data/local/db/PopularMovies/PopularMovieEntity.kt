package com.example.moviesapp.data.local.db.PopularMovies
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moviesapp.data.local.db.ListTypeConverter
import com.example.moviesapp.domain.Model.Movie

/**
 * Created by Ahmed Rabee for AREEB task on 10/14/2023
 */

@Entity(tableName = "popular_movies")
data class PopularMovieEntity(
    @PrimaryKey
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    @TypeConverters(ListTypeConverter::class)
    val genreIds: List<Int>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)
fun mapToDomain(movieEntity: PopularMovieEntity): Movie {
    return Movie(
        id = movieEntity.id,
        adult = movieEntity.adult,
        backdropPath = movieEntity.backdropPath,
        genreIds = movieEntity.genreIds,
        originalLanguage = movieEntity.originalLanguage,
        originalTitle = movieEntity.originalTitle,
        overview = movieEntity.overview,
        popularity = movieEntity.popularity,
        posterPath = movieEntity.posterPath,
        releaseDate = movieEntity.releaseDate,
        title = movieEntity.title,
        video = movieEntity.video,
        voteAverage = movieEntity.voteAverage,
        voteCount = movieEntity.voteCount
    )
}


