package com.example.moviesapp.domain.repos

import androidx.paging.PagingData
import com.example.moviesapp.data.local.db.Config.AppConfigEntity
import com.example.moviesapp.data.resource.Resource
import com.example.moviesapp.domain.Model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getConfig(): AppConfigEntity?
    suspend fun setConfig(language: String, region: String)
    suspend fun getMoviesFromDatabase(): List<Movie>
    suspend fun fetchAndSaveMovies(page: Int, isoCode: String, region: String)
    fun getMoviesStream(language: String, region: String): Flow<PagingData<Movie>>
}
