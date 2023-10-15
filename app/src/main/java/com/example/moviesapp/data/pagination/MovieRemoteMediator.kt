package com.example.moviesapp.data.pagination

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.moviesapp.data.local.db.PopularMovies.PopularMovieEntity
import com.example.moviesapp.data.local.db.PopularMovies.PopularMoviesDao
import com.example.moviesapp.data.remote.MovieApiService
import com.example.moviesapp.data.remote.dto.PopularPlayingMovies.mapToEntity

/**
 * Created by Ahmed Rabee for AREEB task on 10/15/2023
 */
@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val moviesDao: PopularMoviesDao,
    private val service: MovieApiService,
    private val language: String,
    private val region: String
) : RemoteMediator<Int, PopularMovieEntity>() {

    private var lastPage = 0 // Keep track of the last page

    override suspend fun load(loadType: LoadType, state: PagingState<Int, PopularMovieEntity>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                lastPage = 1
                lastPage
            }
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                lastPage++
                lastPage
            }
        }

        return try {
            val apiResponse = service.getPopularMovies(page, language, region)
            val movies = apiResponse.results ?: emptyList()

            if (movies.isNotEmpty()) {
                moviesDao.insertMovies(movies.map { it.mapToEntity() })
            }

            MediatorResult.Success(endOfPaginationReached = movies.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}

