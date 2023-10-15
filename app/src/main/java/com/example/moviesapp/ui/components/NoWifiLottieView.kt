package com.example.moviesapp.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*
import com.example.moviesapp.R


@Composable
fun NoWifiLottieView(modifier: Modifier = Modifier, animationFile : Int = R.raw.no_wifi) {
    var isPlaying by remember {
        mutableStateOf(true)
    }

// for speed
    var speed by remember {
        mutableStateOf(0.5f)
    }
    val composition by rememberLottieComposition(

        LottieCompositionSpec
            .RawRes(animationFile)
    )

    // to control the animation
    val progress by animateLottieCompositionAsState(
        composition,
        isPlaying = isPlaying,
        speed = speed,
        restartOnPlay = false

    )

    LottieAnimation(composition = composition, progress = progress, modifier = modifier)
}