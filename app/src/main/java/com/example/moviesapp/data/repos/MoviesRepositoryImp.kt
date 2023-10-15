package com.example.moviesapp.data.repos

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.moviesapp.data.local.db.Config.AppConfigDao
import com.example.moviesapp.data.local.db.Config.AppConfigEntity
import com.example.moviesapp.data.local.db.PopularMovies.PopularMoviesDao
import com.example.moviesapp.data.local.db.PopularMovies.mapToDomain
import com.example.moviesapp.data.pagination.NowPlayingMoviesPager
import com.example.moviesapp.data.remote.MovieApiService
import com.example.moviesapp.data.remote.dto.PopularPlayingMovies.mapToEntity
import com.example.moviesapp.data.remote.dto.PopularPlayingMovies.toDomainModel
import com.example.moviesapp.domain.Model.Movie
import com.example.moviesapp.domain.repos.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Ahmed Rabee for AREEB task on 10/14/2023
 */

@Singleton
class MoviesRepositoryImp @Inject constructor(
    private val moviesDao: PopularMoviesDao,
    private val service: MovieApiService,
    private val configDao: AppConfigDao,
    private val firebaseAnalytics: FirebaseAnalytics? = null   // Injected FirebaseAnalytics instance
) : MoviesRepository {

    override suspend fun getConfig(): AppConfigEntity? {
        return try {
            configDao.getAppConfig()
        } catch (e: Exception) {
            // Logging failure to fetch application configuration
            firebaseAnalytics?.logEvent("config_operation_failed") {
                param("operation", "fetch")
                param("error_message", e.message ?: "Unknown error")
            }
            null  // or throw or return specific error
        }
    }

    override suspend fun setConfig(language: String, region: String) {
        try {
            val config = AppConfigEntity(language = language, region = region)
            configDao.insertConfig(config)
        } catch (e: Exception) {
            // Logging failure to set application configuration
            firebaseAnalytics?.logEvent("config_operation_failed") {
                param("operation", "insert")
                param("error_message", e.message ?: "Unknown error")
            }
            // handle error as you see fit
        }
    }

    override suspend fun getMoviesFromDatabase(): List<Movie> {
        return try {
            moviesDao.getMovies().map { mapToDomain(it) }
        } catch (e: Exception) {
            // Logging failure to fetch movies from database
            firebaseAnalytics?.logEvent("movie_database_operation_failed") {
                param("operation", "fetch")
                param("error_message", e.message ?: "Unknown error")
            }
            emptyList()  // or throw or return specific error
        }
    }

    override suspend fun fetchAndSaveMovies(page: Int, isoCode: String, region: String) {
        try {
            val response = service.getPopularMovies(page, isoCode, region)
            val movieEntities = response.results.map { it.mapToEntity() }
            moviesDao.insertMovies(movieEntities)
        } catch (e: IOException) {
            // Logging failure to fetch movies from API (network related)
            firebaseAnalytics?.logEvent("movie_api_operation_failed") {
                param("operation", "fetch")
                param("error_message", e.message ?: "Unknown error")
                param("error_type", "network")
            }
            // Handle network error
        } catch (e: Exception) {
            // Logging failure to insert movies into database
            firebaseAnalytics?.logEvent("movie_database_operation_failed") {
                param("operation", "insert")
                param("error_message", e.message ?: "Unknown error")
            }
            // Handle other types of errors
        }
    }

    override fun getMoviesStream(language: String, region: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { NowPlayingMoviesPager(service, language, region) }
        ).flow.map { it.map { dto -> dto.toDomainModel() } }
            .catch { e ->
                // Logging failure to get a stream of movies
                firebaseAnalytics?.logEvent("movie_stream_operation_failed") {
                    param("operation", "stream")
                    param("error_message", e.message ?: "Unknown error")
                }
                // Handle exception and emit some UI state or data if necessary
            }
    }
}

