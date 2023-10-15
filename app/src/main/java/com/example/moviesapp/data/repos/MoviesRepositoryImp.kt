package com.example.moviesapp.data.repos

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.moviesapp.data.local.db.Config.AppConfigDao
import com.example.moviesapp.data.local.db.Config.AppConfigEntity
import com.example.moviesapp.data.local.db.PopularMovies.PopularMoviesDao
import com.example.moviesapp.data.local.db.PopularMovies.mapToDomain
import com.example.moviesapp.data.pagination.MovieRemoteMediator
import com.example.moviesapp.data.remote.MovieApiService
import com.example.moviesapp.data.remote.dto.PopularPlayingMovies.mapToEntity
import com.example.moviesapp.data.remote.dto.PopularPlayingMovies.toDomainModel
import com.example.moviesapp.domain.Model.Movie
import com.example.moviesapp.domain.repos.IMoviesRepository
import com.example.moviesapp.ui.utils.toPagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
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
) : IMoviesRepository {

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


    override suspend fun fetchAndSaveMovies(page: Int, isoCode: String, region: String) {
        val response = service.getPopularMovies(page, isoCode, region)
        val movieEntities = response.results.map { it.mapToEntity() }
        moviesDao.insertMovies(movieEntities)
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getMoviesStream(language: String, region: String): Flow<PagingData<Movie>> {
        val pagingSourceFactory = { moviesDao.getMovies() }

        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            remoteMediator = MovieRemoteMediator(moviesDao, service, language, region),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData -> pagingData.map { mapToDomain(it) } }
    }
}

