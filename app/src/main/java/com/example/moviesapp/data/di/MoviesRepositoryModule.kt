package com.softaai.mvvmdemo.di.moviesmodule

import com.example.moviesapp.data.local.db.Config.AppConfigDao
import com.example.moviesapp.data.local.db.MovieDetails.MovieDetailsDao
import com.example.moviesapp.data.local.db.PopularMovies.PopularMoviesDao
import com.example.moviesapp.data.remote.MovieApiService
import com.example.moviesapp.data.repos.MovieDetailsRepository
import com.example.moviesapp.data.repos.MoviesRepositoryImp
import com.example.moviesapp.domain.repos.IMovieDetailsRepository
import com.example.moviesapp.domain.repos.MoviesRepository
import com.google.firebase.analytics.FirebaseAnalytics
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
        service: MovieApiService,
        moviesDao: PopularMoviesDao,
        configDao: AppConfigDao,
        firebaseAnalytics: FirebaseAnalytics
    ): MoviesRepository {
        return MoviesRepositoryImp(moviesDao, service, configDao, firebaseAnalytics)
    }

    @Provides
    @Singleton
    fun provideMovieDetailsRepository(
        movieApiService: MovieApiService,
        movieDetailsDao: MovieDetailsDao
    ): IMovieDetailsRepository {
        return MovieDetailsRepository(movieApiService, movieDetailsDao)
    }
}



