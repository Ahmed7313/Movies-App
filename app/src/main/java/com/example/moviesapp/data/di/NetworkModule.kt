package com.example.moviesapp.data.di

import com.example.moviesapp.data.NetworkChecker.AndroidNetworkHelper
import com.example.moviesapp.data.NetworkChecker.NetworkChecker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
 * Created by Ahmed Rabee for AREEB task on 10/15/2023
 */
@InstallIn(SingletonComponent::class)
@Module
abstract class NetworkModule {
    @Binds
    abstract fun bindNetworkChecker(helper: AndroidNetworkHelper): NetworkChecker
}
