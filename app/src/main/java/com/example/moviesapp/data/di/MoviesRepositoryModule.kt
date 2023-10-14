package com.softaai.mvvmdemo.di.moviesmodule

import com.example.moviesapp.data.remote.MovieApiService
import com.example.moviesapp.data.repos.MovieRepositoryImpl
import com.example.moviesapp.domain.repos.MovieRepository
import com.softaai.mvvmdemo.data.source.local.roomdb.dao.MovieDao
import com.softaai.mvvmdemo.data.source.local.roomdb.dao.PopularMoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
class MoviesRepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepositoryImpl(
        movieApiService: MovieApiService,
        popularMoviesDao: PopularMoviesDao,
        movieDao: MovieDao
    ): MovieRepository = MovieRepositoryImpl(movieApiService, popularMoviesDao, movieDao)

}