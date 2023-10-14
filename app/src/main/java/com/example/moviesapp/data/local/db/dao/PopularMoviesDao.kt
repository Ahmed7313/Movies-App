package com.softaai.mvvmdemo.data.source.local.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.softaai.mvvmdemo.data.source.local.roomdb.entity.PopularMoviesEntity


@Dao
interface PopularMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovies(popularMoviesEntity: PopularMoviesEntity)
}