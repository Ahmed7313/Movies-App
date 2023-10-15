package com.example.moviesapp.data.local.db.PopularMovies

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Ahmed Rabee for AREEB task on 10/14/2023
 */
@Dao
interface PopularMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<PopularMovieEntity>)

    @Query("SELECT * FROM popular_movies")
    fun getMovies(): PagingSource<Int, PopularMovieEntity>
}