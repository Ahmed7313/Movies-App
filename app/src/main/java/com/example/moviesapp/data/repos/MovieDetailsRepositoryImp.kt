package com.example.moviesapp.data.repos

import com.example.moviesapp.data.local.db.MovieDetails.MovieDetailsDao
import com.example.moviesapp.data.local.db.MovieDetails.MovieDetailsEntity
import com.example.moviesapp.data.remote.MovieApiService
import com.example.moviesapp.data.remote.dto.MovieDetailsDTO.mapToEntity
import com.example.moviesapp.data.resource.Resource
import com.example.moviesapp.domain.repos.IMovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ahmed Rabee for AREEB task on 10/15/2023
 */
@Singleton
class MovieDetailsRepository @Inject constructor(
    private val movieApiService: MovieApiService,
    private val movieDetailsDao: MovieDetailsDao
) : IMovieDetailsRepository{

    override fun getMovieDetails(movieId: Int, language : String): Flow<Resource<MovieDetailsEntity>> = flow {
        emit(Resource.Loading())

        val localData = movieDetailsDao.getMovieDetailsById(movieId)
        if (localData != null) {
            emit(Resource.Success(localData))
        } else {
            try {
                val response = movieApiService.getMovieDetails(movieId,language)
                val entity = response.mapToEntity(response)
                movieDetailsDao.insertMovieDetails(entity)
                emit(Resource.Success(entity))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "An error occurred"))
            }
        }
    }


}