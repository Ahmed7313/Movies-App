package com.example.moviesapp.ui.screens.MoviesScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.resource.Resource
import com.softaai.mvvmdemo.domain.model.Movie
import com.softaai.mvvmdemo.domain.usecase.GetPopularMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ahmed Rabee for AREEB task on 10/14/2023
 */
@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMovies: GetPopularMovies
) : ViewModel() {

    private val _state = mutableStateOf(MovieUiState())
    val state: State<MovieUiState> = _state

    init {
        getMovies()
    }

    fun getMovies() {
        viewModelScope.launch {

            getPopularMovies().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            moviesList = result.data ?: emptyList(),
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            moviesList = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            moviesList = result.data ?: emptyList(),
                            isLoading = false
                        )

                    }
                }
            }.launchIn(this)
        }
    }
}

data class MovieUiState(
    val moviesList: List<Movie> = emptyList(),
    val isLoading: Boolean = false
)