package com.softaai.mvvmdemo.domain.usecase

import androidx.paging.PagingData
import com.example.moviesapp.data.resource.Resource
import com.example.moviesapp.domain.Model.Movie
import com.example.moviesapp.domain.repos.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class GetPopularMoviesUseCase(private val moviesRepository: MoviesRepository) {

    fun execute(language: String, region: String): Flow<PagingData<Movie>> {
        return moviesRepository.getMoviesStream(language, region)
    }
}

