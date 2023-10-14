package com.softaai.mvvmdemo.domain.usecase

import com.example.moviesapp.data.resource.Resource
import com.example.moviesapp.domain.repos.MovieRepository
import com.softaai.mvvmdemo.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class GetPopularMovies(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<Resource<List<Movie>>> {
        return movieRepository.getPopularMovies()
    }
}