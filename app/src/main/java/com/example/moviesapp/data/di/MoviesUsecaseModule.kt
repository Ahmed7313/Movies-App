package com.softaai.mvvmdemo.di.moviesmodule

import com.example.moviesapp.data.NetworkChecker.NetworkChecker
import com.example.moviesapp.domain.repos.IMoviesRepository
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
    fun provideGetPopularMoviesUseCase(
        repository: IMoviesRepository,
        networkChecker: NetworkChecker
    ): GetPopularMoviesUseCase =
        GetPopularMoviesUseCase(repository, networkChecker)
}