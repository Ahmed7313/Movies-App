package com.example.moviesapp.domain.repos

import com.example.moviesapp.data.local.db.MovieDetails.MovieDetailsEntity
import com.example.moviesapp.data.resource.Resource
import com.example.moviesapp.domain.Model.MovieDetails.MovieDetails
import kotlinx.coroutines.flow.Flow
import org.intellij.lang.annotations.Language

/**
 * Created by Ahmed Rabee for AREEB task on 10/15/2023
 */
interface IMovieDetailsRepository {
    fun getMovieDetails(movieId: Int, language: String): Flow<Resource<MovieDetailsEntity>>
}