package com.example.moviesapp.ui.screens.MoviesScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviesapp.data.local.db.Config.AppConfigEntity
import com.example.moviesapp.domain.Model.Movie
import com.example.moviesapp.domain.usecase.GetAppConfigUseCase
import com.softaai.mvvmdemo.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

/**
 * Created by Ahmed Rabee for AREEB task on 10/14/2023
 */
@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMovies: GetPopularMoviesUseCase,
    private val getAppConfig: GetAppConfigUseCase
) : ViewModel() {


    // Fetching the AppConfig using the new UseCase
    private val configFlow: Flow<AppConfigEntity?> = getAppConfig.execute()

    // Using the fetched AppConfig to execute getPopularMovies
    fun getMoviesFlow() : Flow<PagingData<Movie>> = configFlow.flatMapLatest { config ->
        val language = config?.language ?: "en-US"  // Default value if null
        val region = config?.region ?: "US"  // Default value if null
        getPopularMovies.execute(language, region)
    }.cachedIn(viewModelScope)
}
