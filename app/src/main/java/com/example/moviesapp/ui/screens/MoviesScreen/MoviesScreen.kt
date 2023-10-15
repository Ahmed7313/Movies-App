package com.example.moviesapp.ui.screens.MoviesScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.moviesapp.ui.components.MovieShimmerEffectItem
import com.example.moviesapp.ui.components.NoWifiLottieView
import com.example.moviesapp.ui.screens.destinations.MovieDetailsScreenDestination
import com.example.moviesapp.ui.theme.ExtraLargePadding
import com.example.moviesapp.ui.theme.PurpleGrey40
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * Created by Ahmed Rabee for AREEB task on 10/14/2023
 */
@RootNavGraph(start = true) // sets this as the start destination of the default nav graph
@Destination
@Composable
fun MovieListScreen(
    moviesViewModel: MoviesViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {


    val popularMovies = moviesViewModel.getMoviesFlow().collectAsLazyPagingItems()


    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(color = PurpleGrey40),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {

        item {
            Text(
                text = "TMDB List Of Popular Movies",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(ExtraLargePadding)
            )
        }

        items(popularMovies, key = {it.id}){ movie ->
            MovieItem(
                posterImage = movie?.posterPath?: "",
                movieName = movie?.originalTitle ?: "",
                releaseDate = movie?.releaseDate ?: "",
                rating = movie?.voteAverage ?: 0.0){
                navigator.navigate(MovieDetailsScreenDestination(movieId = movie?.id ?: 0) , onlyIfResumed = false )
            }
        }

        when (val state = popularMovies.loadState.refresh) { //FIRST LOAD
            is LoadState.Error -> {
                //TODO Error Item
                //state.error to get error message
                item {
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
            }
            is LoadState.Loading -> { // Loading UI
                item {

                    Column(
                        modifier = Modifier
                            .fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                    }
                }
            }
            else -> {
                if (popularMovies.itemSnapshotList.isEmpty()){
                    item {
                        Column(
                            modifier = Modifier
                                .fillParentMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {

                            Text(text = "No Movies To show")
                        }
                    }
                }

            }
        }

        when (val state = popularMovies.loadState.append) { // Pagination
            is LoadState.Error -> {
                //TODO Pagination Error Item
                //state.error to get error message
                item {
                    Text(text = "Something went Wrong!")

                }
            }
            is LoadState.Loading -> { // Pagination Loading UI
                item {
                    MovieShimmerEffectItem()
                }
            }
            else -> {}
        }
    }
}
