package com.example.moviesapp.data.local.db.Config

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Ahmed Rabee for AREEB task on 10/14/2023
 */
@Entity(tableName = "app_config")
data class AppConfigEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 1,  // single row table
    val language: String,
    val region: String
)