package com.example.moviesapp.data.local.db.Config

import javax.inject.Inject

/**
 * Created by Ahmed Rabee for AREEB task on 10/14/2023
 */
class AppConfigHelper @Inject constructor(
    private val configDao: AppConfigDao
) {
    suspend fun getConfig(): Pair<String, String> {
        val config = configDao.getAppConfig()
        return Pair(config?.language ?: "en", config?.region ?: "US")
    }
}
