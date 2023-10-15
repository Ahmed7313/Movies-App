package com.example.moviesapp.data.local.db.Config

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Ahmed Rabee for AREEB task on 10/14/2023
 */
@Dao
interface AppConfigDao {

    @Query("SELECT * FROM app_config WHERE id = 1")
    suspend fun getAppConfig(): AppConfigEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConfig(config: AppConfigEntity)
}
