package com.softaai.mvvmdemo.domain.usecase

import androidx.paging.PagingData
import com.example.moviesapp.data.NetworkChecker.NetworkChecker
import com.example.moviesapp.domain.Model.Movie
import com.example.moviesapp.domain.repos.IMoviesRepository
import com.example.moviesapp.ui.utils.toPagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class GetPopularMoviesUseCase(
    private val moviesRepository: IMoviesRepository,
    private val networkChecker: NetworkChecker

) {
    fun execute(language: String, region: String): Flow<PagingData<Movie>> {
        return moviesRepository.getMoviesStream(language, region)
    }
}

