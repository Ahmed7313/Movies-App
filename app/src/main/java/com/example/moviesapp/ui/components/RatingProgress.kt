package com.example.moviesapp.ui.components


import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesapp.ui.theme.ExtraSmallPadding
import kotlinx.coroutines.delay

/**
 * Created by Ahmed Rabee for AREEB task on 10/15/2023
 */

@Composable
fun RatingProgress(voteAverage: Double,
                   modifier: Modifier = Modifier,
                   radiuSize : Int = 32,
                   fontSize : Int = 12
) {
    var progress by remember { mutableStateOf(0f) }

    val targetValue = (voteAverage / 10).toFloat()

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = "animate", block = {
        progress = 0f
        val target = targetValue
        val animationDuration = 1000L  // 3 seconds
        val delayStep = 50L // 50 milliseconds
        val steps = animationDuration / delayStep
        val increment = target / steps

        repeat(steps.toInt()) {
            delay(delayStep)
            progress += increment
        }
        progress = target
    })

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(radiuSize.dp)
    ) {
        CircularProgressIndicator(
            progress = progress,
            strokeWidth = ExtraSmallPadding,
            color = Color.Yellow
        )
        Text(
            text = "%.1f".format(voteAverage),
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}





