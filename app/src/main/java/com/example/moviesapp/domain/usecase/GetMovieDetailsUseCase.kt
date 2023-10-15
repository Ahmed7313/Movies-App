package com.example.moviesapp.domain.usecase

import com.example.moviesapp.data.local.db.Config.AppConfigDao
import com.example.moviesapp.data.local.db.MovieDetails.toDomainModel
import com.example.moviesapp.data.resource.Resource
import com.example.moviesapp.domain.Model.MovieDetails.MovieDetails
import com.example.moviesapp.domain.repos.IMovieDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Ahmed Rabee for AREEB task on 10/15/2023
 */

class GetMovieDetailsUseCase @Inject constructor(
    private val movieDetailsRepository: IMovieDetailsRepository,
    private val configDao: AppConfigDao
) {
    fun execute(movieId: Int): Flow<Resource<MovieDetails>> = flow {
        val configEntity = configDao.getAppConfig()
        val language = configEntity?.language ?: "en-US"  // Default value if null

        emit(Resource.Loading<MovieDetails>())

        try {
            val movieDetailsFlow = movieDetailsRepository.getMovieDetails(movieId, language)
            movieDetailsFlow.collect { resource ->
                when (resource) {
                    is Resource.Loading -> emit(Resource.Loading<MovieDetails>())
                    is Resource.Success -> {
                        val domainModel = resource.data?.toDomainModel() // Assuming toDomainModel() maps from Entity to MovieDetails
                        if (domainModel != null) {
                            emit(Resource.Success(domainModel))
                        } else {
                            emit(Resource.Error<MovieDetails>("Data is null"))
                        }
                    }
                    is Resource.Error -> emit(Resource.Error<MovieDetails>(resource.message ?: "An error occurred"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error<MovieDetails>(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}








