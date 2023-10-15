package com.example.moviesapp.data.remote

import com.example.moviesapp.data.remote.dto.MovieDetailsDTO.MovieDetailsDTO
import com.example.moviesapp.data.remote.dto.PopularPlayingMovies.PopularMoviesDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApiService {


    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("language") isoCode: String,
        @Query("region") region: String
    ): PopularMoviesDTO

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") isoCode: String = "en-US"
    ): MovieDetailsDTO

}