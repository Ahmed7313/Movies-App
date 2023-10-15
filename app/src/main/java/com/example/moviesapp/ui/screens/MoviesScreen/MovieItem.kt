package com.example.moviesapp.ui.screens.MoviesScreen

import androidx.compose.runtime.Composable

/**
 * Created by Ahmed Rabee for AREEB task on 10/14/2023
 */
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesapp.data.remote.dto.helper.ApiParams.BASE_URL
import com.example.moviesapp.ui.components.RatingProgress
import com.example.moviesapp.ui.theme.ImageDefaultHeight
import com.example.moviesapp.ui.theme.ImageDefaultWidth
import com.example.moviesapp.ui.theme.MediumPadding
import com.example.moviesapp.ui.utils.ImageUtils
import com.example.moviesapp.ui.utils.shimmerBackground
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieItem(
    posterImage: String, // You can replace this with an Image URL if you're using Coil or Glide
    movieName: String,
    releaseDate: String,
    rating: Double,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(MediumPadding)
            .fillMaxWidth()
            .clip(RoundedCornerShape(MediumPadding)),
        onClick = { onClick() }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            // Poster Image
            Timber.tag("imageCheck").i(ImageUtils.getFullImageUrl(posterImage))
            Image(
                painter = rememberAsyncImagePainter(model = ImageUtils.getFullImageUrl(posterImage)),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(ImageDefaultHeight)
                    .width(ImageDefaultWidth)
                    .shimmerBackground(shape = RectangleShape, active = true)
            )

            // Movie details
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MediumPadding),
                horizontalAlignment = Alignment.End
            ) {

                RatingProgress(voteAverage = rating)

                Text(
                    text = movieName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Left
                )
                Text(
                    text = releaseDate,
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
        }
    }
}