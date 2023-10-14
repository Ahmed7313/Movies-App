package com.example.moviesapp.ui.screens.MoviesScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Created by Ahmed Rabee for AREEB task on 10/14/2023
 */
@Composable
fun MovieListScreen(moviesViewModel: MoviesViewModel = hiltViewModel()) {

    val state = moviesViewModel.state.value

    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {

    }
}
