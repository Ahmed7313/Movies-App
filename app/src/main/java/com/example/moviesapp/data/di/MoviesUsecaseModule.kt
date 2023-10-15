package com.softaai.mvvmdemo.di.moviesmodule

import com.example.moviesapp.domain.repos.MoviesRepository
import com.softaai.mvvmdemo.domain.usecase.GetPopularMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
class MoviesUsecaseModule {

    @Provides
    @Singleton
    fun provideGetPopularMoviesUseCase(repository: MoviesRepository): GetPopularMoviesUseCase =
        GetPopularMoviesUseCase(repository)
}