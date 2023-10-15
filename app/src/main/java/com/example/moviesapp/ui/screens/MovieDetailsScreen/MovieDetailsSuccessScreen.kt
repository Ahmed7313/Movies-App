package com.example.moviesapp.ui.screens.MovieDetailsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.moviesapp.domain.Model.MovieDetails.MovieDetails
import com.example.moviesapp.ui.theme.MediumPadding

/**
 * Created by Ahmed Rabee for AREEB task on 10/15/2023
 */
@Composable
fun MovieDetailsSuccessScreen (movie: MovieDetails?){


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
                HeaderDetailsScreenContent(modifier = Modifier, movie = movie)

                Surface(
                    modifier = Modifier
                        .padding(MediumPadding)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(MediumPadding),
                    color = Color.Gray.copy(alpha = 0.5f)
                ) {
                    Text(
                        text = movie?.overview ?: "",
                        modifier = Modifier
                            .padding(MediumPadding),
                        style = TextStyle(fontSize = 16.sp)
                    )
                }

                if (movie?.productionCompanies?.isEmpty() != true) {
                    Text(
                        text = "Production Companies",
                        modifier = Modifier
                            .padding(MediumPadding),
                        style = TextStyle(fontSize = 16.sp)
                    )
                }

                ProductionCompaniesRow(
                    movie?.productionCompanies ?: listOf(),
                )

    }
}