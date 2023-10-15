package com.example.moviesapp.ui.screens.MovieDetailsScreen

import android.widget.Space
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesapp.domain.Model.MovieDetails.MovieDetails
import com.example.moviesapp.ui.components.RatingProgress
import com.example.moviesapp.ui.screens.MoviesScreen.MovieItem
import com.example.moviesapp.ui.theme.ExtraLargePadding
import com.example.moviesapp.ui.theme.ImageDefaultHeight
import com.example.moviesapp.ui.theme.ImageDefaultWidth
import com.example.moviesapp.ui.theme.MediumPadding
import com.example.moviesapp.ui.theme.Shapes
import com.example.moviesapp.ui.theme.SmallPadding
import com.example.moviesapp.ui.utils.ImageUtils

/**
 * Created by Ahmed Rabee for AREEB task on 10/15/2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsHeaderContent(movie: MovieDetails?,
) {

    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing), // Speeding up the animation
            repeatMode = RepeatMode.Reverse
        )
    )

        Box(
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
        ) {
            // Background Image
            if (movie != null) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    shape = RoundedCornerShape(bottomStart = MediumPadding, bottomEnd = MediumPadding),
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(ImageUtils.getFullImageUrl(movie.backdropPath)),
                        contentDescription = "Background Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer(
                                scaleX = scale,
                                scaleY = scale
                            )
                            .alpha(0.5f)
                            // Clip the Image as well
                            .clip(
                                RoundedCornerShape(
                                    bottomStart = MediumPadding,
                                    bottomEnd = MediumPadding
                                )
                            )
                    )
                }

                // Movie Item
                Card(
                    modifier = Modifier
                        .padding(MediumPadding)
                        .align(Alignment.CenterStart)
                        .clip(RoundedCornerShape(MediumPadding))
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(ImageUtils.getFullImageUrl(movie.posterPath)),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .height(ImageDefaultHeight)
                            .width(ImageDefaultWidth)
                    )
                }

                RatingProgress(
                    voteAverage = movie.voteAverage,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(ExtraLargePadding),
                    radiuSize = 40,
                    fontSize = 20
                )

                // Rectangle Card
                Surface(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(MediumPadding)
                        .fillMaxWidth(0.4f),
                    shape = RoundedCornerShape(MediumPadding),
                    color = Color.Gray.copy(alpha = 0.5f) // Set color here
                ) {
                    Column(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Status",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        )
                        Text(
                            text = movie.status ?: "Unknown",
                            style = TextStyle(
                                fontSize = 16.sp
                            )
                        )

                        Spacer(modifier = Modifier
                            .fillMaxWidth()
                            .height(SmallPadding)
                            .padding(MediumPadding)
                            .background(color = Color.White, shape = Shapes.small))

                        movie?.genres?.take(3)?.forEach { genre ->
                            Text(text = genre.name, style = TextStyle(fontSize = 14.sp))
                        }

                        Spacer(modifier = Modifier
                            .fillMaxWidth()
                            .height(SmallPadding)
                            .padding(MediumPadding)
                            .background(color = Color.White, shape = Shapes.small))

                        Text(
                            text = "Budget: ",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        )
                        Text(
                            text = "${movie.budget}",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        )
                        Spacer(modifier = Modifier
                            .fillMaxWidth()
                            .height(SmallPadding)
                            .padding(MediumPadding)
                            .background(color = Color.White, shape = Shapes.small))

                        Text(
                            text = "Revenue: ",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        )
                        Text(
                            text = "${movie.revenue}",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        )
                    }
                }


                // New TextView for the title of the movie and the date
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(top = ExtraLargePadding, start = ExtraLargePadding)
                ) {
                    Spacer(modifier = Modifier.height(ExtraLargePadding))
                    Text(text = movie.title, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp))
                    Text(text = movie.releaseDate, style = TextStyle(fontSize = 16.sp))
                }
            }
        }

}

