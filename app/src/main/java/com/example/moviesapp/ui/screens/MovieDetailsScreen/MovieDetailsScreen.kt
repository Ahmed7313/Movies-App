package com.example.moviesapp.ui.screens.MovieDetailsScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviesapp.data.resource.Resource
import com.example.moviesapp.ui.components.NoWifiLottieView
import com.example.moviesapp.ui.theme.ExtraLargePadding
import com.example.moviesapp.ui.theme.MediumPadding
import com.example.moviesapp.ui.theme.PurpleGrey40
import com.ramcosta.composedestinations.annotation.Destination

/**
 * Created by Ahmed Rabee for AREEB task on 10/15/2023
 */

@SuppressLint("SuspiciousIndentation")
@Destination
@Composable
fun MovieDetailsScreen (
    movieId : Int,
    movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()
){

    val movieDetails by movieDetailsViewModel.movieDetails.collectAsState()

    // Trigger the initial fetch
    LaunchedEffect(movieId) {
        movieDetailsViewModel.fetchMovieDetails(movieId)
    }

    when (movieDetails) {
        is Resource.Loading -> {
            // Show a loading indicator
            Column(  modifier = Modifier
                .background(color = PurpleGrey40)
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(modifier = Modifier)
            }
        }
        is Resource.Success -> {
            val movie = (movieDetails as Resource.Success).data
            MovieDetailsSuccessScreen(movie = movie)
        }
        is Resource.Error -> {
            // Show an error state
            Column(  modifier = Modifier
                .background(color = PurpleGrey40)
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
                ) {
                NoWifiLottieView(modifier = Modifier
                    .fillMaxWidth()
                    .padding(ExtraLargePadding))
            }

        }
        else -> {}
    }
}