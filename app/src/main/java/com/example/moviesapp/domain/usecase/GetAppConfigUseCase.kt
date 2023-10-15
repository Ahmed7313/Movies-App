package com.example.moviesapp.domain.usecase

import com.example.moviesapp.data.local.db.Config.AppConfigEntity
import com.example.moviesapp.domain.repos.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Ahmed Rabee for AREEB task on 10/14/2023
 */
class GetAppConfigUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    fun execute(): Flow<AppConfigEntity?> {
        return flow {
            val config = moviesRepository.getConfig()
            emit(config)
        }
    }
}
