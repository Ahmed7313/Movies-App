package com.softaai.mvvmdemo.di.moviesmodule

import android.content.Context
import androidx.room.Room
import com.example.moviesapp.data.local.db.Config.AppConfigDao
import com.example.moviesapp.data.local.db.MovieDatabase
import com.example.moviesapp.data.local.db.MovieDetails.MovieDetailsDao
import com.example.moviesapp.data.local.db.PopularMovies.PopularMoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MoviesDatabaseModule {

    @Singleton
    class DatabaseProvider @Inject constructor(
        @ApplicationContext context: Context
    ) {
        val database: MovieDatabase by lazy {
            Room.databaseBuilder(
                context,
                MovieDatabase::class.java,
                "popular_movies.db"
            ).fallbackToDestructiveMigration().build()
        }
    }

    @Provides
    fun providePopularMoviesDao(databaseProvider: DatabaseProvider): PopularMoviesDao {
        return databaseProvider.database.popularMoviesDao()
    }

    @Provides
    fun provideAppConfigDao(databaseProvider: DatabaseProvider): AppConfigDao {
        return databaseProvider.database.appConfigDao()
    }

    @Provides
    fun provideMovieDetailsDao(databaseProvider: DatabaseProvider): MovieDetailsDao {
        return databaseProvider.database.movieDetailsDao()
    }
}
