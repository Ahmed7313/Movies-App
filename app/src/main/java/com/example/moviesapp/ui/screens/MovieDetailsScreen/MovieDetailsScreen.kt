package com.example.moviesapp.ui.screens.MovieDetailsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.moviesapp.ui.components.HeaderDetailsScreenContent
import com.example.moviesapp.ui.theme.MediumPadding
import com.ramcosta.composedestinations.annotation.Destination

/**
 * Created by Ahmed Rabee for AREEB task on 10/15/2023
 */

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
        }
        is Resource.Success -> {
            val movie = (movieDetails as Resource.Success).data
            Column {
                HeaderDetailsScreenContent(modifier = Modifier) {
                    MovieDetailsHeaderContent(movie)
                }
                Surface(
                    modifier = Modifier
                        .padding(MediumPadding)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(MediumPadding),
                    color = Color.Gray.copy(alpha = 0.5f) // Set color here
                ) {
                    Text(
                        text = movie?.overview ?: "",
                        modifier = Modifier
                            .padding(MediumPadding),
                        style = TextStyle(fontSize = 16.sp)
                    )
                }

                if (movie?.productionCompanies?.isEmpty() != true)
                Text(
                    text ="Production Companies",
                    modifier = Modifier
                        .padding(MediumPadding),
                    style = TextStyle(fontSize = 16.sp)
                )
                ProductionCompaniesRow(
                    movie?.productionCompanies ?: listOf(),
                )
            }
        }
        is Resource.Error -> {
            // Show an error state
        }
        else -> {}
    }
}