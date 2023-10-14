package com.example.moviesapp.domain.repos

import com.example.moviesapp.data.resource.Resource
import com.softaai.mvvmdemo.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<Resource<List<Movie>>>
}