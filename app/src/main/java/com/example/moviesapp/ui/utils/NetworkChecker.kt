package com.example.moviesapp.ui.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Ahmed Rabee for AREEB task on 10/15/2023
 */
fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}