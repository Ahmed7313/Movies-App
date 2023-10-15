package com.example.moviesapp.data.local.db.MovieDetails

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Ahmed Rabee for AREEB task on 10/15/2023
 */
@Dao
interface MovieDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetails(movieDetails: MovieDetailsEntity)

    @Query("SELECT * FROM movie_details WHERE id = :movieId")
    suspend fun getMovieDetailsById(movieId: Int): MovieDetailsEntity?
}