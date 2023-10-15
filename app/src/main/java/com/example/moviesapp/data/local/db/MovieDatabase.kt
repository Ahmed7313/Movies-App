package com.example.moviesapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviesapp.data.local.db.Config.AppConfigDao
import com.example.moviesapp.data.local.db.Config.AppConfigEntity
import com.example.moviesapp.data.local.db.MovieDetails.MovieDetailsDao
import com.example.moviesapp.data.local.db.MovieDetails.MovieDetailsEntity
import com.example.moviesapp.data.local.db.PopularMovies.PopularMovieEntity
import com.example.moviesapp.data.local.db.PopularMovies.PopularMoviesDao

/**
 * Created by Ahmed Rabee for AREEB task on 10/14/2023
 */
@Database(entities = [PopularMovieEntity::class, AppConfigEntity::class, MovieDetailsEntity::class], version = 2, exportSchema = false)
@TypeConverters(ListTypeConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun popularMoviesDao(): PopularMoviesDao

    abstract fun appConfigDao(): AppConfigDao

    abstract fun movieDetailsDao(): MovieDetailsDao
}
