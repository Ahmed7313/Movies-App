package com.example.moviesapp.ui.utils

import androidx.paging.PagingData

/**
 * Created by Ahmed Rabee for AREEB task on 10/15/2023
 */
fun <T : Any> List<T>.toPagingData(): PagingData<T> {
    return PagingData.from(this)
}