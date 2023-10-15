package com.example.moviesapp.ui.screens.MovieDetailsScreen

import androidx.lifecycle.ViewModel
import com.example.moviesapp.data.resource.Resource
import com.example.moviesapp.domain.Model.MovieDetails.MovieDetails
import com.example.moviesapp.domain.usecase.GetAppConfigUseCase
import com.example.moviesapp.domain.usecase.GetMovieDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ahmed Rabee for AREEB task on 10/15/2023
 */
@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetails: GetMovieDetailsUseCase
) : ViewModel() {

    private val _movieDetails = MutableStateFlow<Resource<MovieDetails>>(Resource.Loading())
    val movieDetails: StateFlow<Resource<MovieDetails>> = _movieDetails

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _movieDetails.value = Resource.Loading()

            // Fetch movie details
            getMovieDetails.execute(movieId).collect { fetchedDetails ->
                // Update the _movieDetails state
                _movieDetails.value = fetchedDetails
            }
        }
    }
}







