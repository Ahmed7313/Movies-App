package com.example.moviesapp.ui.screens.MovieDetailsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.ColorUtils
import com.example.moviesapp.domain.Model.MovieDetails.MovieDetails

/**
 * Created by Ahmed Rabee for AREEB task on 10/15/2023
 */

@Composable
fun HeaderDetailsScreenContent(
    modifier: Modifier = Modifier,
    movie : MovieDetails?,
) {
    val scrollState = rememberScrollState()

    Box(modifier = modifier.fillMaxWidth()) {
        // This Box will be The content which which scrolls
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            MovieDetailsHeaderContent(movie)
        }

        // This Box is The AppBar on top of the content
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(calculateAlpha(scrollState.value))
                .background(calculateColor(scrollState.value))
        ) {
            // AppBar content here
        }
    }
}

@Composable
fun calculateAlpha(scrollOffset: Int): Float {
    // You can customize this as needed
    val minOffset = 0
    val maxOffset = 500
    return ((scrollOffset - minOffset).coerceIn(minOffset, maxOffset) / maxOffset.toFloat())
}

@Composable
fun calculateColor(scrollOffset: Int): Color {
    val baseColor = android.graphics.Color.TRANSPARENT
    val finalColor = android.graphics.Color.BLACK

    val alpha = calculateAlpha(scrollOffset)
    return Color(
        ColorUtils.blendARGB(
            baseColor,
            finalColor,
            alpha
        )
    )
}